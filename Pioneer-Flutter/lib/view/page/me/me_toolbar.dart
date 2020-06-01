import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-MeTAB-toolbar
class MeToolbar extends AppBar {
  MeToolbar(context)
      : super(
          title: Text(
            ThemeStrings.NAV_ME,
            style: TextStyle(fontSize: ThemeDimens.font_size_medium),
          ),
          centerTitle: true,
          brightness: Brightness.light,
        );
}
