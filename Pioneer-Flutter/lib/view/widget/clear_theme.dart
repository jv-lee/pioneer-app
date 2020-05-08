import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class ClearTheme extends StatelessWidget {
  ClearTheme({Key key,this.child }) : super(key: key);

  final Widget child;

  @override
  Widget build(BuildContext context) {
    return Theme(
      data: ThemeData(
        highlightColor: Color.fromRGBO(0, 0, 0, 0),
        splashColor: Color.fromRGBO(0, 0, 0, 0),
      ),
      child: child,
    );
  }
}
