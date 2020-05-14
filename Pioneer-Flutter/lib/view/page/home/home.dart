import 'package:flutter/material.dart';

import 'home_tab_page.dart';
import 'home_toolbar.dart';

/// @author jv.lee
/// @date 2020/4/30
/// @description 主页-HomeTAB
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
          child: HomeToolbar(),
        ),
        Expanded(
          flex: 1,
          child: HomeTabPage(),
        ),
      ],
    );
  }
}
