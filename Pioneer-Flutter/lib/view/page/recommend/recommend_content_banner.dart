import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_swiper/flutter_swiper.dart';
import 'package:pioneer_flutter/constants/http_constants.dart';
import 'package:pioneer_flutter/model/banner_entity.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-推荐TAB-Banner组件
class RecommendContentBanner extends StatefulWidget {
  final List<BannerData> data;

  RecommendContentBanner({this.data}) : super();

  @override
  State<StatefulWidget> createState() {
    return _RecommendContentBannerState();
  }
}

class _RecommendContentBannerState extends State<RecommendContentBanner> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 180,
      child: buildBanner(),
    );
  }

  Widget buildBanner() {
    if (widget.data == null || widget.data.length == 0) {
      return Center(
        child: Text(
          '加载中...',
          style: TextStyle(color: Theme.of(context).accentColor),
        ),
      );
    } else {
      return Swiper(
          autoplay: true,
          duration: 300,
          itemCount: widget.data.length,
          itemBuilder: (BuildContext context, int index) {
            return CachedNetworkImage(
              imageUrl: widget.data[index].image,
              imageBuilder: (context, imageProvider) => Container(
                decoration: BoxDecoration(
                  image:
                      DecorationImage(image: imageProvider, fit: BoxFit.cover),
                ),
              ),
              placeholder: (context, url) => Container(
                  color: Theme.of(context).primaryColor,
                  child: Center(
                    child: Text('加载中...',
                        style: TextStyle(color: Theme.of(context).accentColor)),
                  )),
              errorWidget: (context, url, error) => Icon(Icons.error),
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
