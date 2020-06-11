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

  int _darkMode;

  static bool isDark = false;

  int get darkMode => _darkMode;

  DarkModeProvider() {
    _init();
  }

  _init() async {
    int mode = await Night.getDefaultNightMode;
    print("初始化模式$mode");
    if (mode != MODE_LIGHT && mode != MODE_DARK) {
      _darkMode = MODE_SYSTEM;
    } else {
      _darkMode = mode;
    }
    isDark = _darkMode == MODE_DARK ? true : false;
    notifyListeners();
  }

  void _changeMode(int darkMode) async {
    _darkMode = darkMode;
    isDark = _darkMode == MODE_DARK ? true : false;
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
