import 'package:flutter/material.dart';
import 'package:pioneer_flutter/route/route_name.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/view/widget/search_text.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-HomeTAB-toolbar组件
class HomeToolbar extends AppBar {
  HomeToolbar(context)
      : super(
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
                    Navigator.pushNamed(context, RouteNames.SEARCH)
                  },
                ),
              ),
              Expanded(
                flex: 0,
                child: Image(
                  image: AssetImage('images/ic_logo.png'),
                  width: ThemeDimens.search_logo_size,
                  height: ThemeDimens.search_logo_size,
                ),
              ),
            ],
          ),
        );
}
