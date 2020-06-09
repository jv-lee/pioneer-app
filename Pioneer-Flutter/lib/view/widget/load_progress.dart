import 'package:flutter/material.dart';

/// @author jv.lee
/// @date 2020/6/1
/// @description
class LoadProgress extends StatefulWidget {
  LoadProgress(this.animationController);

  final AnimationController animationController;

  @override
  State<StatefulWidget> createState() {
    return _LoadProgressState();
  }
}

class _LoadProgressState extends State<LoadProgress> {
  Animation _animation;
  bool firstLoad = true;
  bool visible = false;

  @override
  void initState() {
    super.initState();
    _animation = Tween(begin: 0.0, end: 1.0).animate(CurvedAnimation(
        parent: widget.animationController, curve: Curves.easeIn))
      ..addStatusListener((status) {
        if (_animation.isCompleted) {
          visible = true;
        }
        setState(() {});
      })
      ..addListener(() {
        if (_animation.value >= 0.3 && firstLoad) {
          widget.animationController.stop();
          firstLoad = false;
        }
        setState(() {});
      });
  }

  @override
  void dispose() {
    super.dispose();
    if (!widget.animationController.isDismissed) {
      widget.animationController.dispose();
    }
  }

  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
      animation: _animation,
      builder: (context, child) {
        return AnimatedOpacity(
          duration: Duration(milliseconds: 1),
          opacity: visible == false ? 1.0 : 0.0,
          child: LinearProgressIndicator(
            value: _animation.value,
          ),
        );
      },
    );
  }
}
