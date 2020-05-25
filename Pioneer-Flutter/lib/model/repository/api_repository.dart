import 'package:dio/dio.dart';
import 'package:pioneer_flutter/http/http_manager.dart';
import 'package:pioneer_flutter/model/banner_entity.dart';
import 'package:pioneer_flutter/model/category_entity.dart';
import 'package:pioneer_flutter/model/content_entity.dart';

import '../hot_entity.dart';

/// @author jv.lee
/// @date 2020/5/18
/// @description 网络数据提供
class ApiRepository {
  factory ApiRepository() => _getInstance();

  static ApiRepository get instance => _getInstance();
  static ApiRepository _instance;
  Dio _dio;

  ApiRepository._internal() {
    _dio = HttpManager.instance.getDio();
  }

  static ApiRepository _getInstance() {
    if (_instance == null) {
      _instance = new ApiRepository._internal();
    }
    return _instance;
  }

  Future<BannerEntity> getBannerAsync() async {
    var response = await _dio.get("banners");
    if (response.statusCode == 200) {
      BannerEntity bannerEntity = BannerEntity.fromJson(response.data);
      if (bannerEntity.status == 100) {
        return bannerEntity;
      }
    }
    throw Exception("response status error");
  }

  Future<CategoryEntity> getCategoriesAsync() async {
    var response = await _dio.get("categories/Article");
    if (response.statusCode == 200) {
      var categoryEntity = CategoryEntity.fromJson(response.data);
      if (categoryEntity.status == 100) {
        return categoryEntity;
      }
    }
    throw Exception("response status error");
  }

  Future<ContentEntity> getContentDataAsync(category, type, page, count) async {
    var response = await _dio
        .get("data/category/$category/type/$type/page/$page/count/$count");
    if (response.statusCode == 200) {
      var contentEntity = ContentEntity.fromJson(response.data);
      if (contentEntity.status == 100) {
        return contentEntity;
      }
    }
    return null;
  }

  Future<HotEntity> getHotDataAsync(hotType, category, count) async {
    var response =
        await _dio.get("hot/$hotType/category/$category/count/$count");
    if (response.statusCode == 200) {
      var hotEntity = HotEntity.fromJson(response.data);
      return hotEntity.status == 100 ? hotEntity : null;
    }
    return null;
  }

  getSearchDataAsync(search, category, type, page, count) async {
    var response = await _dio.get(
        "search/$search/category/$category/type/$type/page/$page/count/$count");
    if (response.statusCode == 200) {
      var contentEntity = ContentEntity.fromJson(response.data);
      return contentEntity.status == 100 ? contentEntity : null;
    }
    return null;
  }
}
