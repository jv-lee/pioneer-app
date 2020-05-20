import 'package:flutter/material.dart';
import 'package:pioneer_flutter/tools/status_tools.dart';

/// @author jv.lee
/// @date 2020/5/20
/// @description
class GirlStatusBar extends StatefulWidget {
  GirlStatusBar(this.scrollController) : super();

  final ScrollController scrollController;

  @override
  _GirlStatusBarState createState() => _GirlStatusBarState();
}

class _GirlStatusBarState extends State<GirlStatusBar> {
  int _number = 0;

  @override
  void initState() {
    super.initState();
    widget.scrollController.addListener(() {
      setState(() {
        print(widget.scrollController.position);
        if (widget.scrollController.offset < 86) {
          var offset = widget.scrollController.offset.toInt() / 86;
          _number = (offset * 255).toInt();
        } else {
          _number = 255;
        }
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      height: StatusTools.getStatusHeight(),
      color: Theme.of(context).canvasColor.withAlpha(_number),
    );
  }
}
