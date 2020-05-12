import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/http/http_manager.dart';
import 'package:pioneer_flutter/model/banner_entity.dart';
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
  List<ContentData> contentData = List<ContentData>();
  List<BannerData> bannerData = List<BannerData>();
  StatusPageEnum _status = StatusPageEnum.loading;

  @override
  void initState() {
    super.initState();
    getBannerDate();
    getContentData();
  }

  getBannerDate() async {
    var response = await HttpManager.instance.getDio().get('banners');
    var banner = BannerEntity.fromJson(response.data);
    setState(() {
      bannerData.addAll(banner.data);
    });
  }

  getContentData() async {
    var response = await HttpManager.instance
        .getDio()
        .get('hot/views/category/GanHuo/count/20');
    var content = ContentEntity.fromJson(response.data);
    setState(() {
      contentData.addAll(content.data);
      _status = StatusPageEnum.data;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        margin: EdgeInsets.only(top: 1),
        color: Theme.of(context).backgroundColor,
        child: StatusPage(
          status: _status,
          child: ListView.builder(
              padding: EdgeInsets.all(0),
              itemCount: _headCount + contentData.length,
              itemBuilder: (BuildContext context, int index) {
                if (index == 0) {
                  return RecommendContentBanner(
                    data: bannerData,
                  );
                }
                var entity = contentData[index - _headCount];
                if (entity.images.length == 0) {
                  return ContentTextItem(
                    data: entity,
                  );
                } else if (entity.images.length == 1) {
                  return ContentSingleItem(
                    data: entity,
                  );
                } else {
                  return ContentMultipleItem(
                    data: entity,
                  );
                }
              }),
        ));
  }
}
