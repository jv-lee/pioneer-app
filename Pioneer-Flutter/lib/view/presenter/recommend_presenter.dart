import 'package:pioneer_flutter/model/banner_entity.dart';
import 'package:pioneer_flutter/model/hot_entity.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';

/// @author jv.lee
/// @date 2020/5/22
/// @description
class RecommendPresenter {
  Future<BannerEntity> getBannerDateAsync() {
    return ApiRepository.instance.getBannerAsync();
  }

  Future<HotEntity> getContentDataAsync() {
    return ApiRepository.instance.getHotDataAsync("views", "GanHuo", 20);
  }
}
