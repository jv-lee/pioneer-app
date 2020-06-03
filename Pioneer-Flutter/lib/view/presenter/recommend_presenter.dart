import 'package:pioneer_flutter/constants/recommend_constants.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/control/recommend_control.dart';

/// @author jv.lee
/// @date 2020/5/22
/// @description
class RecommendPresenter {
  RecommendPresenter(this.control) : super();
  final RecommendControl control;
  List<ContentData> viewData;
  List<ContentData> likeData;
  List<ContentData> commendData;

  getBannerDate() {
    ApiRepository.instance.getBannerAsync().then((value) {
      control.bindBanner(value.data);
    }).catchError((error) {
      print('bindBanner ERROR.');
    });
  }

  getContentData(String type) {
    var data = getCacheContentList(type);
    if (data != null) {
      control.bindContent(data);
      return;
    }
    ApiRepository.instance.getHotDataAsync(type, "GanHuo", 20).then((value) {
      if (value.data == null) {
        control.pageError();
      } else {
        putCacheContentList(type, value.data);
        control.bindContent(value.data);
      }
    }).catchError((error) {
      control.pageError();
    });
  }

  putCacheContentList(String type, List<ContentData> data) {
    switch (type) {
      case RecommendConstants.KEY_VIEWS:
        viewData = data;
        break;
      case RecommendConstants.KEY_LIKES:
        likeData = data;
        break;
      case RecommendConstants.KEY_COMMENDS:
        commendData = data;
        break;
    }
  }

  List<ContentData> getCacheContentList(String type) {
    switch (type) {
      case RecommendConstants.KEY_VIEWS:
        return viewData;
      case RecommendConstants.KEY_LIKES:
        return likeData;
      case RecommendConstants.KEY_COMMENDS:
        return commendData;
    }
    return null;
  }
}
