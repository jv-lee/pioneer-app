import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/item/content_multiple_item.dart';
import 'package:pioneer_flutter/view/item/content_single_item.dart';
import 'package:pioneer_flutter/view/item/content_text_item.dart';
import 'package:pioneer_flutter/view/page/recommend/recommend_content_banner.dart';
import 'package:pioneer_flutter/view/widget/status_page.dart';

class RecommendContent extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return RecommendContentState();
  }
}

class RecommendContentState extends State<RecommendContent> {
  var _itemCount = 0;
  StatusPageEnum _status = StatusPageEnum.loading;

  @override
  void initState() {
    super.initState();
    setState(() {
      _itemCount = 4;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Theme
          .of(context)
          .backgroundColor,
      child: Flex(direction: Axis.vertical,children: <Widget>[
        Expanded(flex: 0,child: CupertinoButton(child: Text('title'), onPressed: () {
          setState(() {
            _status = StatusPageEnum.data;
          });
        },),),
        Expanded(flex: 1,child: StatusPage(
          status: _status,
          child: ListView.builder(
              padding: EdgeInsets.all(0),
              itemCount: _itemCount,
              itemBuilder: (BuildContext context, int index) {
                if (index == 0) {
                  return RecommendContentBanner();
                }
                if (index == 1) {
                  return ContentSingleItem();
                }
                if (index == 2) {
                  return ContentMultipleItem();
                }
                return ContentTextItem();
              }),
        ),)
      ],),
    );
  }
}
