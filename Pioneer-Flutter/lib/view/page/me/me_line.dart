import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-MeTAB-行组件
class MeLine extends StatelessWidget {
  MeLine(
      {key,
      this.marginTop,
      this.startChild,
      this.centerChild,
      this.endChild,
      this.onClick})
      : super(key: key);

  final double marginTop;
  final Widget startChild;
  final Widget centerChild;
  final Widget endChild;
  final GestureTapUpCallback onClick;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      child: Container(
        margin: EdgeInsets.only(top: marginTop == null ? 0 : marginTop),
        color: Theme.of(context).canvasColor,
        padding: EdgeInsets.all(12),
        child: Flex(
          direction: Axis.horizontal,
          children: <Widget>[
            Expanded(
              flex: 0,
              child: startChild == null ? Text('') : startChild,
            ),
            Expanded(
              flex: 0,
              child: centerChild == null ? Text('') : centerChild,
            ),
            Expanded(
              flex: 1,
              child: Align(
                alignment: Alignment.centerRight,
                child: endChild == null ? Text('') : endChild,
              ),
            )
          ],
        ),
      ),
      onTapUp: onClick,
    );
  }
}
