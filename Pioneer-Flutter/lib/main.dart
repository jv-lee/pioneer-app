import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer/theme/theme_colors.dart';

import 'view/page/main/main.dart';


void main() {
  runApp(PioneerApp());
}

class PioneerApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primaryColor: ThemeColors.colorPrimary,
        primaryColorDark: ThemeColors.colorPrimaryDark,
        accentColor: ThemeColors.colorAccent,
        backgroundColor: ThemeColors.colorThemeBackground
      ),
      initialRoute: '/',
      routes: {'/': (context) => MainPage()},
    );
  }
}


