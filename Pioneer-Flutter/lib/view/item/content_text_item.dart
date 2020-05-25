import 'package:flutter/material.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
class ContentTextItem extends StatefulWidget {
  ContentTextItem(this.data) : super();

  final ContentData data;

  @override
  _ContentTextItemState createState() => _ContentTextItemState();
}

class _ContentTextItemState extends State<ContentTextItem> {
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
                widget.data.author,
                style: TextStyle(
                    fontSize: ThemeDimens.font_size_small,
                    color: Theme.of(context).primaryColorDark),
              ),
              Text(
                widget.data.category,
                style: TextStyle(
                    fontSize: ThemeDimens.font_size_small,
                    color: Theme.of(context).primaryColorDark),
              )
            ],
          ),
          Container(
              alignment: Alignment.centerLeft,
              margin: EdgeInsets.only(top: ThemeDimens.margin_large),
              child: Text(widget.data.title,
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
            child: Text(widget.data.desc,
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(
                    color: Theme.of(context).primaryColorDark,
                    fontSize: ThemeDimens.font_size_small),
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
                      widget.data.likeCounts == 0
                          ? ThemeStrings.ITEM_LIKE_TEXT
                          : widget.data.likeCounts.toString(),
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
                      widget.data.views == 0
                          ? ThemeStrings.ITEM_VIEW_TEXT
                          : widget.data.views.toString(),
                      style: TextStyle(
                        fontSize: ThemeDimens.font_size_small_x,
                        color: Theme.of(context).primaryColor,
                      ),
                    )
                  ],
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
