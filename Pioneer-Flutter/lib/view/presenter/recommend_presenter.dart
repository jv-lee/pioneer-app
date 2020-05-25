import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/control/recommend_control.dart';

/// @author jv.lee
/// @date 2020/5/22
/// @description
class RecommendPresenter {
  RecommendPresenter(this.control) : super();
  final RecommendControl control;

  getBannerDate() {
    ApiRepository.instance.getBannerAsync().then((value) {
      control.bindBanner(value.data);
    }).catchError((error) {
      print('bindBanner ERROR.');
    });
  }

  getContentData() {
    ApiRepository.instance.getHotDataAsync("views", "GanHuo", 20).then((value) {
      if(value.data == null) {
        control.pageError();
      }else{
        control.bindContent(value.data);
      }
    }).catchError((error) {
      control.pageError();
    });
  }
}
