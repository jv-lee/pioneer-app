import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:pioneer/theme/theme_colors.dart';
import 'package:pioneer/theme/theme_icons.dart';
import 'package:pioneer/theme/theme_strings.dart';
import 'package:pioneer/tools/status_tools.dart';
import 'package:pioneer/view/page/recommend/recommend.dart';

import '../girl/girl.dart';
import '../home/home.dart';
import '../me/me.dart';

/// @author jv.lee
/// @description 主页
class MainPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MainState();
  }
}

class MainState extends State<MainPage> {
  int _tabIndex = 0;
  var _pageList = [HomePage(), RecommendPage(), GirlPage(), MePage()];

  @override
  void initState() {
    super.initState();
    //监听第一帧绘制完毕后设置沉浸式状态栏
    WidgetsBinding.instance.addPostFrameCallback((callback) {
      StatusTools.transparentStatusBar();
    });
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: AnnotatedRegion<SystemUiOverlayStyle>(
          value: SystemUiOverlayStyle.light, child: _pageList[_tabIndex]),
      bottomNavigationBar: BottomNavigationBar(
        items: <BottomNavigationBarItem>[
          new BottomNavigationBarItem(
              icon: Icon(ThemeIcons.home), title: Text(ThemeStrings.NAV_HOME)),
          new BottomNavigationBarItem(
              icon: Icon(ThemeIcons.recommend),
              title: Text(ThemeStrings.NAV_RECOMMEND)),
          new BottomNavigationBarItem(
              icon: Icon(ThemeIcons.girl), title: Text(ThemeStrings.NAV_GIRL)),
          new BottomNavigationBarItem(
              icon: Icon(ThemeIcons.me), title: Text(ThemeStrings.NAV_ME)),
        ],
        type: BottomNavigationBarType.fixed,
        currentIndex: _tabIndex,
        selectedFontSize: 14.0,
        unselectedFontSize: 14.0,
        iconSize: 24.0,
        onTap: (index) {
          setState(() {
            _tabIndex = index;
          });
        },
        backgroundColor: ThemeColors.colorThemeItem,
        selectedItemColor: ThemeColors.colorThemeAccent,
        unselectedItemColor: ThemeColors.colorThemePrimary,
      ),
    );
  }
}
