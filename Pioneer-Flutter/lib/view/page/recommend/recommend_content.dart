import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/http/http_manager.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
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
  int _headCount = 1;
  List<ContentData> datas = List<ContentData>();

  StatusPageEnum _status = StatusPageEnum.loading;

  @override
  void initState() {
    super.initState();
    getContentData();
  }

  getContentData() async {
    var response = await HttpManager.instance
        .getDio()
        .get('hot/views/category/GanHuo/count/20');
    var content = ContentEntity.fromJson(response.data);
    setState(() {
      datas.addAll(content.data);
      _status = StatusPageEnum.data;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Theme.of(context).backgroundColor,
      child: StatusPage(
        status: _status,
        child: ListView.builder(
            padding: EdgeInsets.all(0),
            itemCount: _headCount + datas.length,
            itemBuilder: (BuildContext context, int index) {
              if(index == 0) {
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
      )
    );
  }
}
