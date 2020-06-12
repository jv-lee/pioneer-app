import 'package:flutter/material.dart';
import 'package:night/night.dart';
import 'package:pioneer_flutter/provider/dark_mode_provider.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:pioneer_flutter/view/widget/selector_icon.dart';

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
        );
}
