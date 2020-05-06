import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer/theme/theme_icons.dart';

class MeLine extends StatelessWidget {
  MeLine(
      {key, this.marginTop, this.startChild, this.centerChild, this.endChild})
      : super(key: key);

  final double marginTop;
  final Widget startChild;
  final Widget centerChild;
  final Widget endChild;

  @override
  Widget build(BuildContext context) {
    return Container(
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
    );
  }
}
