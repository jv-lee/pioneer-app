import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer/view/page/me/me_line.dart';
import 'package:pioneer/theme/theme_dimens.dart';
import 'package:pioneer/theme/theme_icons.dart';
import 'package:pioneer/theme/theme_dimens.dart';

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
            MeLine(
              marginTop: ThemeDimens.margin_item,
              startChild: Icon(ThemeIcons.search),
              centerChild: Container(
                margin:
                    EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                child: Text('消息中心'),
              ),
              endChild: Text('>'),
            ),
            MeLine(
                marginTop: 1,
                startChild: Icon(ThemeIcons.search),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text('消息中心'),
                ),
                endChild: Text('>')),
            MeLine(
                marginTop: 1,
                startChild: Icon(ThemeIcons.search),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text('消息中心'),
                ),
                endChild: Text('>')),
            MeLine(
                marginTop: 1,
                startChild: Icon(ThemeIcons.search),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text('消息中心'),
                ),
                endChild: Text('>')),
            MeLine(
                marginTop: ThemeDimens.margin_item,
                startChild: Icon(ThemeIcons.search),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text('消息中心'),
                ),
                endChild: Text('>')),
            MeLine(
                marginTop: 1,
                startChild: Icon(ThemeIcons.search),
                centerChild: Container(
                  margin:
                      EdgeInsets.only(left: ThemeDimens.view_line_text_margin),
                  child: Text('消息中心'),
                ),
                endChild: Text('>')),
          ],
        ),
      ),
    );
  }
}
