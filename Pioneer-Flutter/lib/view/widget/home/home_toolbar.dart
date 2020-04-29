import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_colors.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/view/widget/common/search_text.dart';

///@author jv.lee
///@description home页面 toolbar组件
class HomeToolbar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return AppBar(
      brightness: Brightness.light,
      backgroundColor: ThemeColors.colorThemeItem,
      title: Flex(
        mainAxisAlignment: MainAxisAlignment.end,
        crossAxisAlignment: CrossAxisAlignment.end,
        direction: Axis.horizontal,
        mainAxisSize: MainAxisSize.max,
        children: <Widget>[
          Expanded(
            flex: 1,
            child: GestureDetector(
              child: SearchText(),
              onTapDown: (tapDownDetails) => {
                //路由跳转位置
              },
            ),
          ),
          Expanded(
            flex: 0,
            child: Image(
              image: AssetImage('images/ic_logo.png'),
              width: ThemeDimens.search_icon_size,
              height: ThemeDimens.search_icon_size,
            ),
          ),
        ],
      ),
    );
  }
}
