import 'dart:io';

import 'package:flutter/material.dart';
import 'package:pioneer_flutter/db/base_database.dart';
import 'package:pioneer_flutter/provider/dark_mode_provider.dart';
import 'package:pioneer_flutter/route/route_anim.dart';
import 'package:pioneer_flutter/route/route_name.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/view/page/collect/collect_page.dart';
import 'package:pioneer_flutter/view/page/details/details_page.dart';
import 'package:pioneer_flutter/view/page/history/history_page.dart';
import 'package:pioneer_flutter/view/page/like/like_page.dart';
import 'package:pioneer_flutter/view/page/message/message_page.dart';
import 'package:pioneer_flutter/view/page/search/search.dart';
import 'package:provider/provider.dart';
import 'package:screen_ratio_adapter/screen_ratio_adapter.dart';

import 'view/page/main/main.dart';

void main() {
  runFxApp(PioneerApp(), uiSize: Size(360, 720));
  BaseDatabase.instance.init();
}

// ignore: must_be_immutable
class PioneerApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    if (Platform.isIOS) {
      return MaterialApp(
        //亮色主题 lightTheme
        theme: themeData,
        //深色主题 darkTheme
        darkTheme: darkThemeData,
        onGenerateRoute: _onGenerateRoute,
        initialRoute: '/',
        home: MainPage(),
      );
    } else {
      return MultiProvider(
        providers: [ChangeNotifierProvider.value(value: DarkModeProvider())],
        child: Consumer<DarkModeProvider>(
          builder: (context, darkModeProvider, _) {
            switch (darkModeProvider.darkMode) {
              case DarkModeProvider.MODE_DARK:
                {
                  //深色主题 darkTheme
                  return MaterialApp(
                    theme: darkThemeData,
                    darkTheme: darkThemeData,
                    onGenerateRoute: _onGenerateRoute,
                    initialRoute: '/',
                    home: MainPage(),
                  );
                }
              case DarkModeProvider.MODE_LIGHT:
                {
                  //亮色主题 lightTheme
                  return MaterialApp(
                    theme: themeData,
                    darkTheme: themeData,
                    onGenerateRoute: _onGenerateRoute,
                    initialRoute: '/',
                    home: MainPage(),
                  );
                }
              default:
                {
                  //跟随系统
                  return MaterialApp(
                    //亮色主题 lightTheme
                    theme: themeData,
                    //深色主题 darkTheme
                    darkTheme: darkThemeData,
                    onGenerateRoute: _onGenerateRoute,
                    initialRoute: '/',
                    home: MainPage(),
                  );
                }
            }
          },
        ),
      );
    }
  }

  Route<dynamic> _onGenerateRoute(RouteSettings settings) {
    var arguments = settings.arguments;
    switch (settings.name) {
      case RouteNames.DETAILS:
        return AnimationPageRoute(
            builder: (_) => ContentDetailsPage(
                  data: arguments,
                ));
      case RouteNames.SEARCH:
        return AnimationPageRoute(builder: (_) => SearchPage(),
        animationType: AnimationType.SlideBottomToTop);
      case RouteNames.MESSAGE:
        return AnimationPageRoute(builder: (_) => MessagePage());
      case RouteNames.LIKE:
        return AnimationPageRoute(builder: (_) => LikePage());
      case RouteNames.HISTORY:
        return AnimationPageRoute(builder: (_) => HistoryPage());
      case RouteNames.COLLECT:
        return AnimationPageRoute(builder: (_) => CollectPage());
      case RouteNames.FEEDBACK:
        return AnimationPageRoute(builder: (_) => HistoryPage());
      default:
        return AnimationPageRoute(
            builder: (_) => MainPage(),
            animationType: AnimationType.SlideRightToLeft,
            pageAffectedType: PageAffectedType.Exit);
    }
  }

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
