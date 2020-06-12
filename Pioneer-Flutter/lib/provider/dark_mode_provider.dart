import 'package:flutter/material.dart';
import 'package:night/night.dart';
import 'package:night/night.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// @author jv.lee
/// @date 2020/6/1
/// @description
class DarkModeProvider with ChangeNotifier {
  static const int MODE_LIGHT = 1; // 亮色模式
  static const int MODE_DARK = 2; // 深色模式
  static const int MODE_SYSTEM = 3; // 跟随系统

  int darkMode;

  DarkModeProvider() {
    _init();
  }

  _init() async {
    print("getDefault ${await Night.getDefaultNightMode}");
    var isSystem = await Night.isSystemTheme;
    if (isSystem) {
      darkMode = MODE_SYSTEM;
    } else {
      darkMode = await Night.isDarkTheme() ? MODE_DARK : MODE_LIGHT;
    }
    print("当前模式$darkMode");
    notifyListeners();
  }

  void _changeMode(int darkMode) async {
    this.darkMode = darkMode;
    if (darkMode == MODE_DARK) {
      Night.updateNightTheme(true);
    } else if (darkMode == MODE_LIGHT) {
      Night.updateNightTheme(false);
    } else {
      Night.updateSystemTheme(true);
    }
    notifyListeners();
  }

  static changeMode(BuildContext context, int darkMode) async {
    Provider.of<DarkModeProvider>(context, listen: false)._changeMode(darkMode);
  }
}
