import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer/theme/theme_strings.dart';
import 'package:pioneer/theme/theme_dimens.dart';

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
