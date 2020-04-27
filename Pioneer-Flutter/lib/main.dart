import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/tools/status_tools.dart';
import 'package:pioneer_flutter/view/page/main.dart';

void main() {
  runApp(PioneerApp());
  StatusTools.transipaerntStatusBar();
}

class PioneerApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/',
      routes: {'/': (context) => MainPage()},
    );
  }
}
