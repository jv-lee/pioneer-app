import 'package:flutter/material.dart';
import 'package:pioneer/theme/theme_colors.dart';

import 'home_tab.dart';
import 'home_toolbar.dart';

/// @author jv.lee
/// @description 主页-主页面
class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomeState();
  }
}

class HomeState extends State<HomePage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Flex(
      direction: Axis.vertical,
      mainAxisSize: MainAxisSize.max,
      children: <Widget>[
        Expanded(
          flex: 0,
          child: Column(
            children: <Widget>[HomeToolbar(), HomeTab()],
          ),
        ),
        Expanded(
          flex: 1,
          child: Container(
            color: ThemeColors.colorThemeBackground,
          ),
        ),
      ],
    );
  }
}
