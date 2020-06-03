import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/view/widget/radio_icon.dart';

/// @author jv.lee
/// @date 2020/6/3
/// @description 主页-推荐TAB-tabs组件
class RecommendContentTab extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _RecommendContentTabState();
  }
}

class _RecommendContentTabState extends State<RecommendContentTab> {
  int groupValue = 1;
  onChange(val) {
    this.setState(() {
      groupValue = val;
      print("val = " + val.toString());
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Theme.of(context).canvasColor,
      margin: EdgeInsets.only(bottom: ThemeDimens.margin_item),
      padding: EdgeInsets.all(ThemeDimens.padding_small),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Container(
            child: RadioIcon(
              value: 1,
              groupValue: groupValue,
              icon: ThemeIcons.views,
              defaultColor: Theme.of(context).primaryColor,
              activeColor: ThemeColors.colorViews,
              changeFunction: (value)=>onChange(value),
            ),
            margin: EdgeInsets.only(
                left: ThemeDimens.margin_large,
                right: ThemeDimens.margin_large),
          ),
          Container(
            child: RadioIcon(
              value: 2,
              groupValue: groupValue,
              icon: ThemeIcons.like,
              defaultColor: Theme.of(context).primaryColor,
              activeColor: ThemeColors.colorLike,
              changeFunction: (value)=>onChange(value),
            ),
            margin: EdgeInsets.only(
                left: ThemeDimens.margin_large,
                right: ThemeDimens.margin_large),
          ),
          Container(
            child: RadioIcon(
              value: 3,
              groupValue: groupValue,
              icon: ThemeIcons.commend,
              defaultColor: Theme.of(context).primaryColor,
              activeColor: ThemeColors.colorMessage,
              changeFunction: (value)=>onChange(value),
            ),
            margin: EdgeInsets.only(
                left: ThemeDimens.margin_large,
                right: ThemeDimens.margin_large),
          ),
        ],
      ),
    );
  }
}
