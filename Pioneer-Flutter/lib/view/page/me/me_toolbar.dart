import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-MeTAB-toolbar
class MeToolbar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: Text(
        ThemeStrings.NAV_ME,
        style: TextStyle(fontSize: ThemeDimens.font_size_medium),
      ),
      centerTitle: true,
      brightness: Brightness.light,
      backgroundColor: Theme.of(context).cardColor,
    );
  }
}
