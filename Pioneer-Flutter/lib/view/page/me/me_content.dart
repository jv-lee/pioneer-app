import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/view/page/me/me_line.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

class MeContent extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MeContentState();
  }
}

class MeContentState extends State<MeContent> {
  @override
  Widget build(BuildContext context) {
    return Container(
      color: Theme.of(context).backgroundColor,
      child: ConstrainedBox(
        constraints: BoxConstraints.expand(),
        child: Column(
          children: <Widget>[
            //消息中心
            MeLine(
              marginTop: ThemeDimens.margin_item,
              startChild: Icon(
                ThemeIcons.message,
                color: ThemeColors.colorMessage,
              ),
              centerChild: Container(
                margin:
                    EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                child: Text(ThemeStrings.ME_ITEM_MESSAGE),
              ),
              endChild:
                  Icon(ThemeIcons.arrow, color: Theme.of(context).primaryColor),
              onClick: (clickDetails) => {print('object')},
            ),
            //我喜欢的
            MeLine(
                marginTop: 1,
                startChild: Icon(
                  ThemeIcons.like,
                  color: ThemeColors.colorLike,
                ),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text(ThemeStrings.ME_ITEM_LIKE),
                ),
                endChild: Icon(ThemeIcons.arrow,
                    color: Theme.of(context).primaryColor)),
            //浏览过的
            MeLine(
                marginTop: 1,
                startChild: Icon(
                  ThemeIcons.views,
                  color: ThemeColors.colorViews,
                ),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text(ThemeStrings.ME_ITEM_VIEWS),
                ),
                endChild: Icon(ThemeIcons.arrow,
                    color: Theme.of(context).primaryColor)),
            //我的收藏
            MeLine(
                marginTop: 1,
                startChild: Icon(
                  ThemeIcons.favorite,
                  color: ThemeColors.colorFavorite,
                ),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text(ThemeStrings.ME_ITEM_FAVORITE),
                ),
                endChild: Icon(ThemeIcons.arrow,
                    color: Theme.of(context).primaryColor)),
            //意见反馈
            MeLine(
                marginTop: ThemeDimens.margin_item,
                startChild: Icon(ThemeIcons.feedback),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text(ThemeStrings.ME_ITEM_FEEDBACK),
                ),
                endChild: Icon(ThemeIcons.arrow,
                    color: Theme.of(context).primaryColor)),
            //清除缓存
            MeLine(
                marginTop: 1,
                startChild: Icon(ThemeIcons.settings),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text(ThemeStrings.ME_ITEM_SETTINGS),
                ),
                endChild: Icon(ThemeIcons.arrow,
                    color: Theme.of(context).primaryColor)),
          ],
        ),
      ),
    );
  }
}
