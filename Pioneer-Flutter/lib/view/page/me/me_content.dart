import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/view/page/me/me_line.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-MeTAB-内容
class MeContent extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _MeContentState();
  }
}

class _MeContentState extends State<MeContent> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        //消息中心
        MeLine(
          marginTop: ThemeDimens.margin_item,
          startChild: Icon(
            ThemeIcons.message,
            color: ThemeColors.colorMessage,
            size: ThemeDimens.view_line_icon_size,
          ),
          centerChild: Container(
            margin: EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
            child: Text(
              ThemeStrings.ME_ITEM_MESSAGE,
              style: TextStyle(color: Theme.of(context).accentColor),
            ),
          ),
          endChild:
              Icon(ThemeIcons.arrow, color: Theme.of(context).primaryColor),
          onClick: (clickDetails) => {Navigator.pushNamed(context, '/message')},
        ),
        //我喜欢的
        MeLine(
          marginTop: 1,
          startChild: Icon(
            ThemeIcons.like,
            color: ThemeColors.colorLike,
            size: ThemeDimens.view_line_icon_size,
          ),
          centerChild: Container(
            margin: EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
            child: Text(ThemeStrings.ME_ITEM_LIKE,
                style: TextStyle(color: Theme.of(context).accentColor)),
          ),
          endChild:
              Icon(ThemeIcons.arrow, color: Theme.of(context).primaryColor),
          onClick: (clickDetails) => {Navigator.pushNamed(context, '/like')},
        ),
        //浏览过的
        MeLine(
            marginTop: 1,
            startChild: Icon(
              ThemeIcons.views,
              color: ThemeColors.colorViews,
              size: ThemeDimens.view_line_icon_size,
            ),
            centerChild: Container(
              margin: EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
              child: Text(ThemeStrings.ME_ITEM_VIEWS,
                  style: TextStyle(color: Theme.of(context).accentColor)),
            ),
            endChild:
                Icon(ThemeIcons.arrow, color: Theme.of(context).primaryColor),
            onClick: (clickDetails) =>
                {Navigator.pushNamed(context, '/history')}),
        //我的收藏
        MeLine(
            marginTop: 1,
            startChild: Icon(
              ThemeIcons.favorite,
              color: ThemeColors.colorFavorite,
              size: ThemeDimens.view_line_icon_size,
            ),
            centerChild: Container(
              margin: EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
              child: Text(ThemeStrings.ME_ITEM_FAVORITE,
                  style: TextStyle(color: Theme.of(context).accentColor)),
            ),
            endChild:
                Icon(ThemeIcons.arrow, color: Theme.of(context).primaryColor),
            onClick: (clickDetails) =>
                {Navigator.pushNamed(context, '/collect')}),
        //意见反馈
        MeLine(
            marginTop: ThemeDimens.margin_item,
            startChild: Icon(
              ThemeIcons.feedback,
              color: ThemeColors.colorDefault,
              size: ThemeDimens.view_line_icon_size,
            ),
            centerChild: Container(
              margin: EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
              child: Text(ThemeStrings.ME_ITEM_FEEDBACK,
                  style: TextStyle(color: Theme.of(context).accentColor)),
            ),
            endChild:
                Icon(ThemeIcons.arrow, color: Theme.of(context).primaryColor),
            onClick: (clickDetails) =>
                {Navigator.pushNamed(context, '/feedback')}),
        //清除缓存
        MeLine(
            marginTop: 1,
            startChild: Icon(
              ThemeIcons.settings,
              color: ThemeColors.colorDefault,
              size: ThemeDimens.view_line_icon_size,
            ),
            centerChild: Container(
              margin: EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
              child: Text(ThemeStrings.ME_ITEM_SETTINGS,
                  style: TextStyle(color: Theme.of(context).accentColor)),
            ),
            endChild:
                Icon(ThemeIcons.arrow, color: Theme.of(context).primaryColor),
            onClick: (clickDetails) => {}),
      ],
    );
  }
}
