import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_banner_swiper/flutter_banner_swiper.dart';
import 'package:pioneer_flutter/http/http_manager.dart';
import 'package:pioneer_flutter/model/BannerEntity.dart';

class RecommendContentBanner extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return RecommendContentBannerState();
  }
}

class RecommendContentBannerState extends State<RecommendContentBanner> {
  List<Data> datas = List<Data>();
  var bannerIndex = 0;

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
    return BannerSwiper(
      height: 160,
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
      getwidget: (index) {
        if (bannerIndex == datas.length) {
          bannerIndex = 0;
        }
        return Container(
          child: Image.network(
            datas[bannerIndex++].image,
            fit: BoxFit.cover,
          ),
        );
      },
    );
  }
}
