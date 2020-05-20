import 'package:flutter/material.dart';

/// @author jv.lee
/// @date 2020/4/30
/// @description 清除主题样式控件
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
