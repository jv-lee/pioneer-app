import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

///@author jv.lee
///@description 临时占位pageWidget
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
