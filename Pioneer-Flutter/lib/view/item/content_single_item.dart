import 'package:flutter/material.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:pioneer_flutter/tools/common_tools.dart';
import 'package:pioneer_flutter/tools/time_tools.dart';

import 'content_item_image.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
class ContentSingleItem extends StatelessWidget {
  ContentSingleItem(this.data) : super();

  final ContentData data;

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(bottom: ThemeDimens.margin_item),
      padding: EdgeInsets.all(ThemeDimens.padding_item),
      color: Theme.of(context).canvasColor,
      child: Column(
        children: <Widget>[
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Text(
                data.author,
                style: TextStyle(
                    fontSize: ThemeDimens.font_size_small,
                    color: Theme.of(context).primaryColorDark),
              ),
              Text(
                data.category,
                style: TextStyle(
                    fontSize: ThemeDimens.font_size_small,
                    color: Theme.of(context).primaryColorDark),
              )
            ],
          ),
          Container(
            margin: EdgeInsets.only(top: ThemeDimens.margin_large),
            height: ThemeDimens.item_content_picture_height,
            child: Row(
              mainAxisSize: MainAxisSize.min,
              children: <Widget>[
                Expanded(
                  flex: 0,
                  child: ContentItemImage(
                    data.images[0],
                    isSingle: true,
                  ),
                ),
                Expanded(
                  flex: 1,
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    children: <Widget>[
                      Expanded(
                        flex: 0,
                        child: Container(
                            alignment: Alignment.centerLeft,
                            child: Text(data.title,
                                textAlign: TextAlign.start,
                                maxLines: 2,
                                overflow: TextOverflow.ellipsis,
                                style: TextStyle(
                                  color: Theme.of(context).accentColor,
                                  fontSize: ThemeDimens.font_size_medium,
                                  fontWeight: FontWeight.bold,
                                ),
                                strutStyle: StrutStyle(
                                    forceStrutHeight: true, leading: 0.1))),
                      ),
                      Expanded(
                        flex: 1,
                        child: Container(
                          margin:
                              EdgeInsets.only(top: ThemeDimens.margin_small),
                          alignment: Alignment.centerLeft,
                          child: Text(data.desc,
                              textAlign: TextAlign.start,
                              maxLines: 3,
                              overflow: TextOverflow.ellipsis,
                              style: TextStyle(
                                  color: Theme.of(context).primaryColorDark,
                                  fontSize: ThemeDimens.font_size_small),
                              strutStyle: StrutStyle(
                                  forceStrutHeight: true, leading: 0.1)),
                        ),
                      )
                    ],
                  ),
                )
              ],
            ),
          ),
          Container(
            margin: EdgeInsets.only(top: ThemeDimens.margin_large),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Row(
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
                        ThemeIcons.views,
                        size: 14,
                        color: Theme.of(context).primaryColor,
                      ),
                      margin: EdgeInsets.only(right: 3, left: 3),
                    ),
                    Text(
                      data.views == 0
                          ? ThemeStrings.ITEM_VIEW_TEXT
                          : data.views.toString(),
                      style: TextStyle(
                        fontSize: ThemeDimens.font_size_small_x,
                        color: Theme.of(context).primaryColor,
                      ),
                    )
                  ],
                ),
                Text(
                  TimeTools.getChineseTimeString(data.publishedAt),
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
