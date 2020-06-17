import 'dart:convert';

import 'package:pioneer_flutter/constants/cache_constants.dart';
import 'package:pioneer_flutter/constants/recommend_constants.dart';
import 'package:pioneer_flutter/model/banner_entity.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/model/hot_entity.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/tools/cache_load.dart';
import 'package:pioneer_flutter/view/control/recommend_control.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// @author jv.lee
/// @date 2020/5/22
/// @description
class RecommendPresenter {
  RecommendPresenter(this.control) {
    _bannerCacheLoad = _BannerCacheLoad(control);
    _contentCacheLoad = _ContentCacheLoad(control, putCacheContentList);
  }

  final RecommendControl control;
  _BannerCacheLoad _bannerCacheLoad;
  _ContentCacheLoad _contentCacheLoad;

  List<ContentData> viewData;
  List<ContentData> likeData;
  List<ContentData> commendData;

  getBannerDate() {
    _bannerCacheLoad.load();
  }

  getContentData(String type) {
    _contentCacheLoad.type = type;

    var data = getCacheContentList(type);
    if (data != null) {
      control.bindData(data);
      return;
    }
    _contentCacheLoad.load();
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

class _BannerCacheLoad extends CacheLoad<BannerEntity> {
  _BannerCacheLoad(this.control);

  RecommendControl control;

  @override
  bindData(BannerEntity entity) {
    control.bindBanner(entity.data);
  }

  @override
  BannerEntity buildEntity(String value) {
    return BannerEntity.fromJson(json.decode(value));
  }

  @override
  Future<String> loadCache() async {
    var sp = await SharedPreferences.getInstance();
    return sp.getString(CacheConstants.KEY_RECOMMEND_BANNER_CACHE);
  }

  @override
  loadError(Exception e) {}

  @override
  Future<BannerEntity> loadNetwork() async {
    return await ApiRepository.instance.getBannerAsync();
  }

  @override
  localSave(BannerEntity entity) async {
    var sp = await SharedPreferences.getInstance();
    sp.setString(CacheConstants.KEY_RECOMMEND_BANNER_CACHE,
        json.encode(entity.toJson()));
  }

  @override
  Map<String, dynamic> toJson(BannerEntity entity) {
    return entity.toJson();
  }
}

class _ContentCacheLoad extends CacheLoad<HotEntity> {
  _ContentCacheLoad(this.control, this.setCacheData);

  RecommendControl control;
  String type;
  PutCacheContentList setCacheData;

  @override
  bindData(HotEntity entity) {
    setCacheData(type, entity.data);
    control.bindData(entity.data);
  }

  @override
  HotEntity buildEntity(String value) {
    return HotEntity.fromJson(json.decode(value));
  }

  @override
  Future<String> loadCache() async {
    var sp = await SharedPreferences.getInstance();
    return sp.getString(CacheConstants.KEY_RECOMMEND_LIST_CACHE + type);
  }

  @override
  loadError(Exception e) {
    control.bindError(e);
  }

  @override
  Future<HotEntity> loadNetwork() async {
    return await ApiRepository.instance.getHotDataAsync(type, "GanHuo", 20);
  }

  @override
  localSave(HotEntity entity) async {
    var sp = await SharedPreferences.getInstance();
    sp.setString(CacheConstants.KEY_RECOMMEND_LIST_CACHE + type,
        json.encode(entity.toJson()));
  }

  @override
  Map<String, dynamic> toJson(HotEntity entity) {
    return entity.toJson();
  }
}

typedef PutCacheContentList = void Function(
    String type, List<ContentData> data);
