import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/view/page/search/search.dart';

import 'view/page/main/main.dart';

void main() {
  runApp(PioneerApp());
}

class PioneerApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      //亮色主题 lightTheme
      theme: ThemeData(
        appBarTheme: AppBarTheme(
            color: ThemeColors.lightColorItem,
            elevation: 0,
            brightness: Brightness.light,
            iconTheme: IconThemeData(color: ThemeColors.lightColorAccent),
            textTheme: TextTheme(
                title: TextStyle(color: ThemeColors.lightColorAccent))),
        bottomAppBarTheme: BottomAppBarTheme(
            color: ThemeColors.lightColorItem, elevation: 5.0),
        scaffoldBackgroundColor: ThemeColors.lightColorBackground,
        primaryColor: ThemeColors.lightColorPrimary,
        primaryColorDark: ThemeColors.lightColorPrimaryDark,
        accentColor: ThemeColors.lightColorAccent,
        backgroundColor: ThemeColors.lightColorBackground,
        canvasColor: ThemeColors.lightColorItem,
        //itemColor
        focusColor: ThemeColors.lightColorSearch,
        //searchColor
        splashColor: ThemeColors.lightColorPlaceholder,
        //placeholderColor
        cursorColor: ThemeColors.lightColorAccent,
      ),
      //深色主题 darkTheme
      darkTheme: ThemeData(
        appBarTheme: AppBarTheme(
            color: ThemeColors.darkColorItem,
            elevation: 0,
            brightness: Brightness.dark,
            iconTheme: IconThemeData(color: ThemeColors.darkColorAccent),
            textTheme: TextTheme(
                title: TextStyle(color: ThemeColors.darkColorAccent))),
        bottomAppBarTheme:
            BottomAppBarTheme(color: ThemeColors.darkColorItem, elevation: 5.0),
        scaffoldBackgroundColor: ThemeColors.darkColorBackground,
        primaryColor: ThemeColors.darkColorPrimary,
        primaryColorDark: ThemeColors.darkColorPrimaryDark,
        accentColor: ThemeColors.darkColorAccent,
        backgroundColor: ThemeColors.darkColorBackground,
        canvasColor: ThemeColors.darkColorItem,
        //itemColor
        focusColor: ThemeColors.darkColorSearch,
        //searchColor
        splashColor: ThemeColors.darkColorPlaceholder,
        //placeholderColor
        cursorColor: ThemeColors.darkColorAccent,
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => MainPage(),
        "/search": (context) => SearchPage()
      },
    );
  }
}
