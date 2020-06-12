import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:night/night.dart';
import 'package:pioneer_flutter/provider/dark_mode_provider.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:pioneer_flutter/tools/cache_tools.dart';
import 'package:pioneer_flutter/tools/toast_tools.dart';
import 'package:pioneer_flutter/view/page/me/me_line.dart';

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
  var cacheValue = "0KB";
  var isSystem = true;
  var isDark = false;

  initNightMode() async {
    isSystem = await Night.isSystemTheme;
    isDark = await Night.isDarkTheme();
    setState(() {});
  }

  @override
  void initState() {
    super.initState();
    _loadCache();
    initNightMode();
  }

  _loadCache() {
    CacheTools.loadCacheAsync().then((value) {
      cacheValue = value;
      setState(() {});
    });
  }

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
            endChild: Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: <Widget>[
                Container(
                  margin: EdgeInsets.only(right: 10),
                  child: Text(
                    cacheValue,
                    style: TextStyle(color: Theme.of(context).primaryColor),
                  ),
                ),
                Icon(ThemeIcons.arrow, color: Theme.of(context).primaryColor)
              ],
            ),
            onClick: (clickDetails) => {
                  showCupertinoDialog(
                      context: context,
                      builder: (context) {
                        return CupertinoAlertDialog(
                          content: Text(
                            '确认清除缓存',
                            style: TextStyle(
                                fontSize: ThemeDimens.font_size_small),
                          ),
                          actions: <Widget>[
                            CupertinoDialogAction(
                                child: Text(ThemeStrings.CANCEL,
                                    style: TextStyle(
                                        fontSize:
                                            ThemeDimens.font_size_medium)),
                                onPressed: () {
                                  Navigator.pop(context);
                                }),
                            CupertinoDialogAction(
                                child: Text(ThemeStrings.CONFIRM,
                                    style: TextStyle(
                                        fontSize:
                                            ThemeDimens.font_size_medium)),
                                onPressed: () {
                                  CacheTools.clearCache(context)
                                      .whenComplete(() {
                                    _loadCache();
                                    Navigator.pop(context);
                                  });
                                })
                          ],
                        ).build(context);
                      })
                }),
        MeLine(
          startChild: Text(ThemeStrings.ME_ITEM_SYSTEM),
          endChild: Switch(
            value: isSystem,
            onChanged: (change) {
              setState(() {
                isSystem = change;
                DarkModeProvider.changeMode(
                    context,
                    isSystem
                        ? DarkModeProvider.MODE_SYSTEM
                        : Night.isDarkTheme() == true
                            ? DarkModeProvider.MODE_DARK
                            : DarkModeProvider.MODE_LIGHT);
              });
            },
          ),
        ),
        MeLine(
          startChild: Text(ThemeStrings.ME_ITEM_DARK),
          endChild: Switch(
            value: isDark,
            onChanged: (change) {
              if (!isSystem) {
                setState(() {
                  isDark = change;
                  DarkModeProvider.changeMode(
                      context,
                      isDark
                          ? DarkModeProvider.MODE_DARK
                          : DarkModeProvider.MODE_LIGHT);
                });
              }
            },
          ),
        )
      ],
    );
  }
}
