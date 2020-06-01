import 'package:flutter/material.dart';
import 'package:pioneer_flutter/provider/dark_mode_provider.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/view/page/search/search.dart';
import 'package:provider/provider.dart';
import 'package:screen_ratio_adapter/screen_ratio_adapter.dart';

import 'view/page/main/main.dart';

void main() {
  runFxApp(PioneerApp(), uiSize: Size(360, 720));
//  runApp(PioneerApp());
}

class PioneerApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [ChangeNotifierProvider.value(value: DarkModeProvider())],
      child: Consumer<DarkModeProvider>(
        builder: (context, darkModeProvider, _) {
          return darkModeProvider.darkMode == DarkModeProvider.MODE_SYSTEM
              ? MaterialApp(
                  //亮色主题 lightTheme
                  theme: themeData,
                  //深色主题 darkTheme
                  darkTheme: darkThemeData,
                  initialRoute: '/',
                  routes: routes,
                )
              : MaterialApp(
                  //亮色主题 lightTheme
                  theme: darkModeProvider.darkMode == DarkModeProvider.MODE_DARK
                      ? darkThemeData
                      : themeData,
                  //深色主题 darkTheme
                  initialRoute: '/',
                  routes: routes,
                );
        },
      ),
    );
  }

  var routes = {
    '/': (context) => MainPage(),
    "/search": (context) => SearchPage()
  };

  var themeData = ThemeData(
    appBarTheme: AppBarTheme(
        color: ThemeColors.lightColorItem,
        elevation: 0,
        brightness: Brightness.light,
        iconTheme: IconThemeData(color: ThemeColors.lightColorAccent),
        textTheme: TextTheme(
            title: TextStyle(
                color: ThemeColors.lightColorAccent,
                fontSize: ThemeDimens.font_size_medium))),
    bottomAppBarTheme:
        BottomAppBarTheme(color: ThemeColors.lightColorItem, elevation: 5.0),
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
  );

  var darkThemeData = ThemeData(
    appBarTheme: AppBarTheme(
        color: ThemeColors.darkColorItem,
        elevation: 0,
        brightness: Brightness.dark,
        iconTheme: IconThemeData(color: ThemeColors.darkColorAccent),
        textTheme: TextTheme(
            title: TextStyle(
                color: ThemeColors.darkColorAccent,
                fontSize: ThemeDimens.font_size_medium))),
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
  );
}
