import 'dart:convert';

import 'package:pioneer_flutter/constants/cache_constants.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/tools/cache_load.dart';
import 'package:pioneer_flutter/view/control/girl_control.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// @author jv.lee
/// @date 2020/5/19
/// @description
class GirlPresenter {
  GirlPresenter(this.control) {
    _contentCacheLoad = _ContentCacheLoad(control);
  }

  final GirlControl control;
  _ContentCacheLoad _contentCacheLoad;

  getContentData(page) {
    _contentCacheLoad.page = page;
    _contentCacheLoad.load();
  }
}

class _ContentCacheLoad extends CacheLoad<ContentEntity> {
  _ContentCacheLoad(this.control);

  GirlControl control;
  int page;

  @override
  bindData(ContentEntity entity) {
    control.bindData(entity);
  }

  @override
  ContentEntity buildEntity(String value) {
    return ContentEntity.fromJson(json.decode(value));
  }

  @override
  Future<String> loadCache() async {
    var sp = await SharedPreferences.getInstance();
    return sp.getString(CacheConstants.KEY_GIRL_LIST_CACHE);
  }

  @override
  loadError() {
    control.pageError();
  }

  @override
  Future<ContentEntity> loadNetwork() {
    return ApiRepository.instance.getContentDataAsync("Girl", "Girl", page, 20);
  }

  @override
  localSave(ContentEntity entity) async {
    var sp = await SharedPreferences.getInstance();
    sp.setString(
        CacheConstants.KEY_GIRL_LIST_CACHE, json.encode(entity.toJson()));
  }

  @override
  Map<String, dynamic> toJson(ContentEntity entity) {
    return entity.toJson();
  }
}
