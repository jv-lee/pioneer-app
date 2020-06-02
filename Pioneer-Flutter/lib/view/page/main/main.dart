import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:pioneer_flutter/tools/status_tools.dart';
import 'package:pioneer_flutter/view/page/recommend/recommend.dart';

import '../girl/girl.dart';
import '../home/home.dart';
import '../me/me.dart';

/// @author jv.lee
/// @date 2020/4/30
/// @description 主页
class MainPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _MainState();
  }
}

class _MainState extends State<MainPage> {
  int _tabIndex = 0;
  var _pageList = [HomePage(), RecommendPage(), GirlPage(), MePage()];
  PageController _pageController;

  @override
  void initState() {
    super.initState();
    //监听第一帧绘制完毕后设置沉浸式状态栏
    WidgetsBinding.instance.addPostFrameCallback((callback) {
      StatusTools.transparentStatusBar();
    });
    _pageController = PageController(initialPage: 0);
  }

  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView.builder(
          itemCount: _pageList.length,
          controller: _pageController,
          physics: NeverScrollableScrollPhysics(), //静止PageView滑动
          itemBuilder: (BuildContext context, int index) {
            return _pageList[index];
          }),
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
            _pageController.jumpToPage(index);
          });
        },
        selectedItemColor: Theme.of(context).accentColor,
        unselectedItemColor: Theme.of(context).primaryColor,
      ),
    );
  }
}
