import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:pioneer_flutter/view/item/content_item_image.dart';

/// @author jv.lee
/// @date 2020/5/27
/// @description
class TestPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(bottom: ThemeDimens.margin_item),
      padding: EdgeInsets.all(ThemeDimens.padding_item),
      color: Theme.of(context).canvasColor,
      child: Column(
        children: <Widget>[
          Container(
            margin: EdgeInsets.only(top: ThemeDimens.margin_large),
            height: ThemeDimens.item_content_picture_height,
            child: Row(
              mainAxisSize: MainAxisSize.min,
              children: <Widget>[
                Expanded(
                  flex: 0,
                  child: ContentItemImage(
                    null,
                    isSingle: true,
                  ),
                ),
                Expanded(
                  child: Column(
                    mainAxisSize: MainAxisSize.max,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      Expanded(
                        flex: 0,
                        child: Container(
                            alignment: Alignment.centerLeft,
                            child: Text(ThemeStrings.CODE,
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
                        child: Container(
                          margin:
                              EdgeInsets.only(top: ThemeDimens.margin_small),
                          alignment: Alignment.centerLeft,
                          child: Text(ThemeStrings.CODE,
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
        ],
      ),
    );
  }
}
