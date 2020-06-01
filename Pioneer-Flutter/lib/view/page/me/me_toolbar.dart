import 'package:flutter/material.dart';
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
            actions: [
              Container(
                margin: EdgeInsets.only(right: ThemeDimens.padding_item),
                child: SelectorIcon(
                  isPress: DarkModeProvider.isDark,
                  normalIcon:
                      Icon(ThemeIcons.light, color: ThemeColors.colorLight),
                  pressIcon:
                      Icon(ThemeIcons.night, color: ThemeColors.colorNight),
                  selectorFunction: (selector) {
                    DarkModeProvider.changeMode(
                        context,
                        selector
                            ? DarkModeProvider.MODE_DARK
                            : DarkModeProvider.MODE_LIGHT);
                  },
                ),
              )
            ]);
}
