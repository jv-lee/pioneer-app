import 'package:flutter/material.dart';

/// @author jv.lee
/// @date 2020/6/1
/// @description
class SelectorIcon extends StatefulWidget {
  SelectorIcon({
    @required this.normalIcon,
    @required this.pressIcon,
    this.selectorFunction,
    this.isPress,
  }) {
    if (isPress == null) {
      isPress = false;
    }
  }

  final Widget normalIcon;
  final Widget pressIcon;
  final SelectorFunction selectorFunction;
  bool isPress;

  @override
  State<StatefulWidget> createState() {
    return _SelectorIconState();
  }
}

class _SelectorIconState extends State<SelectorIcon> {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      child: widget.isPress == false ? widget.normalIcon : widget.pressIcon,
      onTap: () {
        if (widget.isPress) {
          widget.isPress = false;
        } else {
          widget.isPress = true;
        }
        if (widget.selectorFunction != null) {
          widget.selectorFunction(widget.isPress);
        }
        setState(() {});
      },
    );
  }
}

typedef SelectorFunction<T> = void Function(bool isPress);
