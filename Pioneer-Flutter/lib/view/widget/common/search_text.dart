import 'package:flutter/cupertino.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

///@author jv.lee
///@description 项目通用 搜索框text展示样式
class SearchText extends StatelessWidget {
  SearchText({Key key, this.width, this.height})
      : assert(width == null),
        assert(height == null),
        super(key: key);

  final double width;
  final double height;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.fromLTRB(
          ThemeDimens.padding_small, 0, ThemeDimens.padding_small, 0),
      alignment: Alignment.centerLeft,
      margin: EdgeInsets.only(right: ThemeDimens.margin_item),
      width: width == null ? ThemeDimens.search_text_width_home : width,
      height: height == null ? ThemeDimens.search_text_height : height,
      child: Text(
        ThemeStrings.SEARCH_HINT,
        style: TextStyle(
            fontSize: ThemeDimens.font_size_medium,
            color: ThemeColors.colorThemePrimary),
      ),
      decoration: BoxDecoration(
          color: ThemeColors.colorThemeSearch,
          borderRadius:
              BorderRadius.all(Radius.circular(ThemeDimens.common_radius))),
    );
  }
}
