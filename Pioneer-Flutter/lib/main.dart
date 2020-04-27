import 'dart:io';
import 'dart:ui';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:pioneer_flutter/view/page/GirlPage.dart';
import 'package:pioneer_flutter/view/page/HomePage.dart';
import 'package:pioneer_flutter/view/page/MePage.dart';
import 'package:pioneer_flutter/view/page/RecommendPage.dart';

void main() {
  runApp(MyApp());
  if (Platform.isAndroid) {
    SystemUiOverlayStyle systemUiOverlayStyle =
        SystemUiOverlayStyle(statusBarColor: Colors.transparent);
    SystemChrome.setSystemUIOverlayStyle(systemUiOverlayStyle);
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _tabIndex = 0;
  var tabImages;
  var appBarTitles = ['首页','推荐','妹子','我的'];
  var _pageList = [HomePage(),RecommendPage(),GirlPage(),MePage()];

  Text getTabTitle(int curIndex) {
    if (curIndex == _tabIndex) {
      return new Text(appBarTitles[curIndex],
          style: new TextStyle(fontSize: 14.0, color: const Color(0xff1296db)));
    } else {
      return new Text(appBarTitles[curIndex],
          style: new TextStyle(fontSize: 14.0, color: const Color(0xff515151)));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: _pageList[_tabIndex],
      bottomNavigationBar: BottomNavigationBar(items: <BottomNavigationBarItem>[
            new BottomNavigationBarItem(
                icon: Icon(Icons.ac_unit), title: getTabTitle(0)),
            new BottomNavigationBarItem(
                icon: Icon(Icons.ac_unit), title: getTabTitle(1)),
            new BottomNavigationBarItem(
                icon: Icon(Icons.ac_unit), title: getTabTitle(2)),
            new BottomNavigationBarItem(
                icon: Icon(Icons.ac_unit), title: getTabTitle(3)),
      ],
      type: BottomNavigationBarType.fixed,
      currentIndex: _tabIndex,
      selectedFontSize: 14.0,
      unselectedFontSize: 14.0,
      iconSize: 24.0,
      onTap: (index){
        setState(() {
          _tabIndex = index;
        });
      },),
    );
  }
}

// 获取状态栏高度 EdgeInsets.only(top: MediaQueryData.fromWindow(window).padding.top),
