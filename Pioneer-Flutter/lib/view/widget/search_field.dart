import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 项目通用 搜索框text展示样式
class SearchField extends StatefulWidget {
  SearchField({this.onSubmitted}) : super();

  final ValueChanged<String> onSubmitted;

  @override
  State<StatefulWidget> createState() {
    return SearchFieldState();
  }
}

class SearchFieldState extends State<SearchField> {
  @override
  Widget build(BuildContext context) {
    return Flex(
      direction: Axis.horizontal,
      children: <Widget>[
        Expanded(
          flex: 1,
          child: Container(
            padding: EdgeInsets.fromLTRB(
                ThemeDimens.padding_large, 0, ThemeDimens.padding_large, 0),
            alignment: Alignment.centerLeft,
            margin: EdgeInsets.only(right: ThemeDimens.margin_item),
            height: ThemeDimens.search_text_height,
            child: TextField(
              onSubmitted:
                  widget.onSubmitted == null ? (value) {} : widget.onSubmitted,
              textInputAction: TextInputAction.search,
              decoration: InputDecoration(
                  hintStyle: TextStyle(color: Theme.of(context).primaryColor),
                  hintText: ThemeStrings.SEARCH_HINT,
                  border: InputBorder.none,
                  contentPadding: EdgeInsets.only(top: -10, left: -6),
                  icon: Icon(
                    ThemeIcons.search,
                    color: Theme.of(context).primaryColor,
                    size: ThemeDimens.search_icon_size,
                  )),
            ),
            decoration: BoxDecoration(
                color: Theme.of(context).focusColor,
                borderRadius: BorderRadius.all(
                    Radius.circular(ThemeDimens.common_radius))),
          ),
        ),
        Expanded(
            flex: 0,
            child: GestureDetector(
              onTapDown: (tapDownDetails) => {Navigator.pop(context)},
              child: Text(ThemeStrings.CANCEL,
                  style: TextStyle(fontSize: ThemeDimens.font_size_large)),
            ))
      ],
    );
  }
}
