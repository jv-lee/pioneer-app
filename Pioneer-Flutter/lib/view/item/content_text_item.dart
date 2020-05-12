import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

class ContentTextItem extends StatelessWidget {
  ContentTextItem({this.data}) : super();

  final ContentData data;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(bottom: ThemeDimens.margin_item),
      padding: EdgeInsets.all(ThemeDimens.padding_item),
      color: Theme.of(context).canvasColor,
      child: Column(
        children: <Widget>[
          Flex(
            direction: Axis.horizontal,
            children: <Widget>[
              Expanded(
                flex: 0,
                child: Text(
                  'author',
                  style: TextStyle(
                      fontSize: ThemeDimens.font_size_small,
                      color: Theme.of(context).primaryColorDark),
                ),
              ),
              Spacer(
                flex: 1,
              ),
              Expanded(
                flex: 0,
                child: Text(
                  'category',
                  style: TextStyle(
                      fontSize: ThemeDimens.font_size_small,
                      color: Theme.of(context).primaryColorDark),
                ),
              )
            ],
          ),
          Container(
              alignment: Alignment.centerLeft,
              margin: EdgeInsets.only(top: ThemeDimens.margin_large),
              child: Text(data.title,
                  textAlign: TextAlign.start,
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  style: TextStyle(
                    color: Theme.of(context).accentColor,
                    fontSize: ThemeDimens.font_size_medium,
                    fontWeight: FontWeight.bold,
                  ),
                  strutStyle:
                      StrutStyle(forceStrutHeight: true, leading: 0.1))),
          Container(
            alignment: Alignment.centerLeft,
            margin: EdgeInsets.only(top: ThemeDimens.margin_small),
            child: Text(data.desc,
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(
                    color: Theme.of(context).primaryColorDark,
                    fontSize: ThemeDimens.font_size_small),
                strutStyle: StrutStyle(forceStrutHeight: true, leading: 0.1)),
          ),
          Container(
            margin: EdgeInsets.only(top: ThemeDimens.margin_large),
            child: Flex(
              direction: Axis.horizontal,
              children: <Widget>[
                Container(
                  child: Icon(
                    ThemeIcons.like,
                    size: 14,
                    color: Theme.of(context).primaryColor,
                  ),
                  margin: EdgeInsets.only(right: 3),
                ),
                Text(
                  data.likeCounts == 0
                      ? ThemeStrings.ITEM_LIKE_TEXT
                      : data.likeCounts.toString(),
                  style: TextStyle(
                    fontSize: ThemeDimens.font_size_small_x,
                    color: Theme.of(context).primaryColor,
                  ),
                ),
                Container(
                  child: Icon(
                    ThemeIcons.like,
                    size: 14,
                    color: Theme.of(context).primaryColor,
                  ),
                  margin: EdgeInsets.only(right: 3, left: 3),
                ),
                Text(
                  data.views == 0 ? ThemeStrings.ITEM_VIEW_TEXT : data.views.toString(),
                  style: TextStyle(
                    fontSize: ThemeDimens.font_size_small_x,
                    color: Theme.of(context).primaryColor,
                  ),
                ),
                Spacer(
                  flex: 1,
                ),
                Text(
                  '昨天',
                  style: TextStyle(
                    fontSize: ThemeDimens.font_size_small_x,
                    color: Theme.of(context).primaryColor,
                  ),
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}
