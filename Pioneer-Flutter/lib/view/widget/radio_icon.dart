import 'package:flutter/material.dart';

/// @author jv.lee
/// @date 2020/6/1
/// @description
// ignore: must_be_immutable
class RadioIcon extends StatefulWidget {
  RadioIcon({
    @required this.icon,
    @required this.defaultColor,
    @required this.activeColor,
    @required this.value,
    @required this.groupValue,
    this.changeFunction,
  });

  final IconData icon;
  final Color defaultColor;
  final Color activeColor;
  final int value;
  final int groupValue;
  final ChangeFunction changeFunction;

  @override
  State<StatefulWidget> createState() {
    return _RadioIconState();
  }
}

class _RadioIconState extends State<RadioIcon> {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      child: widget.value == widget.groupValue
          ? Icon(
              widget.icon,
              color: widget.activeColor,
            )
          : Icon(
              widget.icon,
              color: widget.defaultColor,
            ),
      onTap: () {
        widget.changeFunction(widget.value);
      },
    );
  }
}

typedef ChangeFunction = void Function(int value);
