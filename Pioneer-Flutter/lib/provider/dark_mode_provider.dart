import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// @author jv.lee
/// @date 2020/6/1
/// @description
class DarkModeProvider with ChangeNotifier {
  static final int MODE_LIGHT = 0; // 亮色模式
  static final int MODE_DARK = 1; // 深色模式
  static final int MODE_STYSTEM = 2; // 跟随系统
  /// 深色模式 0: 关闭 1: 开启 2: 随系统
  int _darkMode;

  int get darkMode => _darkMode;

  void _changeMode(int darkMode) async {
    _darkMode = darkMode;

    notifyListeners();
    SharedPreferences.getInstance().then((sp) {
      sp.setInt("mode", darkMode);
    });
  }

  static changeMode(BuildContext context, int darkMode) async {
    Provider.of<DarkModeProvider>(context, listen: false)._changeMode(darkMode);
  }

  static init(BuildContext context) {
    changeMode(context, 1);
    SharedPreferences.getInstance().then((sp) {
      changeMode(context, 1);
    });
  }
}
