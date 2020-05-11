import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
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
    return Container(
      height: 180,
      child: buildBanner(),
    );
  }

  Widget buildBanner() {
    if (datas.length == 0) {
      return Center(
        child: Text('加载中...'),
      );
    } else {
      return Swiper(
          autoplay: true,
          duration: 300,
          itemCount: datas.length,
          itemBuilder: (BuildContext context, int index) {
            return Image.network(
              datas[index].image,
              fit: BoxFit.cover,
            );
          },
          pagination: SwiperPagination(
              builder: DotSwiperPaginationBuilder(
                  activeSize: 6,
                  size: 6,
                  color: Colors.black54,
                  activeColor: Colors.white)));
    }
  }
}
