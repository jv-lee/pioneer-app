import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 项目通用 搜索框text展示样式
class SearchText extends StatelessWidget {
  SearchText({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.fromLTRB(
          ThemeDimens.padding_large, 0, ThemeDimens.padding_large, 0),
      alignment: Alignment.centerLeft,
      margin: EdgeInsets.only(right: ThemeDimens.margin_item),
      height: ThemeDimens.search_text_height,
      child: Row(
        children: <Widget>[
          Padding(
            padding: EdgeInsets.only(right: ThemeDimens.padding_large),
            child: Icon(ThemeIcons.search,
                color: Theme.of(context).primaryColor,
                size: ThemeDimens.search_icon_size),
          ),
          Text(
            ThemeStrings.SEARCH_HINT,
            style: TextStyle(
                fontSize: ThemeDimens.font_size_medium,
                color: Theme.of(context).primaryColor),
          )
        ],
      ),
      decoration: BoxDecoration(
          color: Theme.of(context).focusColor,
          borderRadius:
              BorderRadius.all(Radius.circular(ThemeDimens.common_radius))),
    );
  }
}
