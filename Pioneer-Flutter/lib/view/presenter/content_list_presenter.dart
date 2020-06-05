import 'dart:convert';

import 'package:pioneer_flutter/constants/cache_constants.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/tools/cache_first_load.dart';
import 'package:pioneer_flutter/view/control/content_list_control.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
class ContentListPresenter {
  ContentListPresenter(this.control) {
    cacheLoad = _ContentDataCacheLoad(control);
  }

  final ContentListControl control;
  _ContentDataCacheLoad cacheLoad;

  getContentDataAsync(type, page) async {
    cacheLoad.page = page;
    cacheLoad.type = type;
    cacheLoad.load();
  }
}

class _ContentDataCacheLoad extends CacheFirstLoad<ContentEntity> {
  _ContentDataCacheLoad(this.control) : super();
  final ContentListControl control;
  String type;
  int page;

  @override
  bindData(entity) {
    control.bindData(entity);
  }

  @override
  buildEntity(String value) {
    return ContentEntity.fromJson(json.decode(value));
  }

  @override
  Future<String> loadCache() async {
    var sp = await SharedPreferences.getInstance();
    return sp.getString(CacheConstants.KEY_HOME_LIST_CACHE + type);
  }

  @override
  loadError() {
    control.pageError();
  }

  @override
  Future<ContentEntity> loadNetwork() {
    return ApiRepository.instance.getContentDataAsync("All", type, page, 20);
  }

  @override
  localSave(entity) async {
    if (entity.page == 1) {
      var sp = await SharedPreferences.getInstance();
      sp.setString(
          CacheConstants.KEY_HOME_LIST_CACHE + type, json.encode(entity));
    }
  }

  @override
  Map<String, dynamic> toJson(ContentEntity entity) {
    return entity.toJson();
  }
}
