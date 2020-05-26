import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/search_text.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-推荐TAB-toolbar
class RecommendToolbar extends AppBar {
  RecommendToolbar(context)
      : super(
            elevation: 0,
            brightness: Brightness.light,
            backgroundColor: Theme.of(context).canvasColor,
            title: GestureDetector(
                child: SearchText(),
                onTapDown: (tapDownDetails) => {
                      //路由跳转位置
                      Navigator.pushNamed(context, '/search')
                    }));
}
