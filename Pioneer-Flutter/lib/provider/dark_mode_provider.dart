import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// @author jv.lee
/// @date 2020/6/1
/// @description
class DarkModeProvider with ChangeNotifier {
  static const int MODE_LIGHT = 0; // 亮色模式
  static const int MODE_DARK = 1; // 深色模式
  static const int MODE_SYSTEM = 2; // 跟随系统
  static const String STORE_KEY = 'darkMode';

  SharedPreferences _sp;

  /// 深色模式 0: 关闭 1: 开启 2: 随系统
  int _darkMode;

  static bool isDark = false;

  int get darkMode => _darkMode;

  DarkModeProvider() {
    _init();
  }

  _init() async {
    this._sp = await SharedPreferences.getInstance();
    int localMode = this._sp.getInt(STORE_KEY);
    _changeMode(localMode ?? MODE_SYSTEM);
  }

  void _changeMode(int darkMode) async {
    _darkMode = darkMode;
    isDark = darkMode == MODE_DARK ? true : false;

    notifyListeners();

    SharedPreferences sp = this._sp ?? SharedPreferences.getInstance();

    await sp.setInt(STORE_KEY, darkMode);
  }

  static changeMode(BuildContext context, int darkMode) async {
    Provider.of<DarkModeProvider>(context, listen: false)._changeMode(darkMode);
  }
}
