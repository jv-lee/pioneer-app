import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/http/http_manager.dart';
import 'package:pioneer_flutter/model/banner_entity.dart';
import 'package:pioneer_flutter/view/widget/banner_page.dart';

class RecommendContentBanner extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return RecommendContentBannerState();
  }
}

class RecommendContentBannerState extends State<RecommendContentBanner> {
  List<Data> datas = List<Data>();

  @override
  void initState() {
    super.initState();
    getBannerDate();
  }

  getBannerDate() async {
    var response = await HttpManager.instance.getDio().get('banners');
    var banner = BannerEntity.fromJson(response.data);
    setState(() {
      datas.addAll(banner.data);
    });
  }

  @override
  Widget build(BuildContext context) {
    return BannerPage(
      height: 120,
      width: 54,
      length: datas.length,
      autoLoop: true,
      spaceMode: false,
      normalWidget: Container(
        width: 6,
        height: 6,
        margin: EdgeInsets.only(left: 2, right: 2),
        decoration: BoxDecoration(
            color: Colors.black38, borderRadius: BorderRadius.circular(6)),
      ),
      selectorWidget: Container(
        width: 6,
        height: 6,
        margin: EdgeInsets.only(left: 2, right: 2),
        decoration: BoxDecoration(
            color: Colors.white, borderRadius: BorderRadius.circular(6)),
      ),
      getWidget: (index) {
        return Container(
          child: Image.network(
            datas[index].image,
            fit: BoxFit.cover,
          ),
        );
      },
    );
  }
}
