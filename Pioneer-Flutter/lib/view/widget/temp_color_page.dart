import 'package:flutter/material.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 临时占位pageWidget
class TempColorPage extends StatelessWidget {
  TempColorPage({Key key, this.color}) : super(key: key);

  final Color color;

  @override
  Widget build(BuildContext context) {
    return Container(
      color: color == null ? Colors.red : color,
    );
  }
}
