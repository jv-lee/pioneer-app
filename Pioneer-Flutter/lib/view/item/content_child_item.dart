import 'package:flutter/material.dart';
import 'package:pioneer_flutter/db/entity/history.dart';
import 'package:pioneer_flutter/route/route_name.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

/// @author jv.lee
/// @date 2020/6/9
/// @description
class ContentChildItem extends StatelessWidget {
  ContentChildItem(this.data) : super();

  final History data;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.pushNamed(context, RouteNames.DETAILS,arguments: History.historyToContent(data));
      },
      child: Container(
        margin: EdgeInsets.only(bottom: ThemeDimens.margin_item),
        padding: EdgeInsets.all(ThemeDimens.padding_item),
        color: Theme.of(context).canvasColor,
        child: Column(
          children: <Widget>[
            Container(
                alignment: Alignment.centerLeft,
                margin: EdgeInsets.only(top: ThemeDimens.margin_large),
                child: Text(data.title,
                    textAlign: TextAlign.start,
                    maxLines: 2,
                    overflow: TextOverflow.ellipsis,
                    style: TextStyle(
                      color: Theme.of(context).accentColor,
                      fontSize: ThemeDimens.font_size_small,
                      fontWeight: FontWeight.bold,
                    ),
                    strutStyle:
                        StrutStyle(forceStrutHeight: true, leading: 0.1))),
            Container(
              alignment: Alignment.centerLeft,
              margin: EdgeInsets.only(top: ThemeDimens.margin_small),
              child: Text(data.description,
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  style: TextStyle(
                      color: Theme.of(context).primaryColorDark,
                      fontSize: ThemeDimens.font_size_small_x),
                  strutStyle: StrutStyle(forceStrutHeight: true, leading: 0.1)),
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
                        data.likeCount == 0
                            ? ThemeStrings.ITEM_LIKE_TEXT
                            : data.likeCount.toString(),
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
                        data.viewCount == 0
                            ? ThemeStrings.ITEM_VIEW_TEXT
                            : data.viewCount.toString(),
                        style: TextStyle(
                          fontSize: ThemeDimens.font_size_small_x,
                          color: Theme.of(context).primaryColor,
                        ),
                      )
                    ],
                  ),
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}
