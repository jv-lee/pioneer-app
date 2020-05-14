import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/search_text.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-推荐TAB-toolbar
class RecommendToolbar extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return  GestureDetector(
      child: AppBar(
        elevation: 1,
        brightness: Brightness.light,
        backgroundColor: Theme.of(context).canvasColor,
        title: Flex(
          children: <Widget>[
            Expanded(
              flex: 1,
              child: SearchText(),
            )
          ],
          direction: Axis.horizontal,
        ),
      ),
      onTapDown: (tapDownDetails) => {
        //路由跳转位置
        Navigator.pushNamed(context, '/search')
      },
    );
  }

}