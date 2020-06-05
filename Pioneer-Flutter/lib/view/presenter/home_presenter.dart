import 'dart:convert';

import 'package:pioneer_flutter/constants/cache_constants.dart';
import 'package:pioneer_flutter/model/category_entity.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/tools/cache_load.dart';
import 'package:pioneer_flutter/view/control/home_control.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
class HomePresenter {
  HomePresenter(this.homeControl) {
    _cacheLoad = _CategoryTabsCacheLoad(homeControl);
  }

  final HomeControl homeControl;
  _CategoryTabsCacheLoad _cacheLoad;

  buildCategoryTabs() {
    _cacheLoad.load();
  }

}

class _CategoryTabsCacheLoad extends CacheLoad<CategoryEntity> {
  _CategoryTabsCacheLoad(this.homeControl) : super();
  final HomeControl homeControl;

  @override
  bindData(CategoryEntity entity) {
    homeControl.bindData(entity.data);
  }

  @override
  CategoryEntity buildEntity(String value) {
    return CategoryEntity.fromJson(json.decode(value));
  }

  @override
  Future<String> loadCache() async {
    var sp = await SharedPreferences.getInstance();
    return sp.get(CacheConstants.KEY_HOME_CACHE);
  }

  @override
  loadError() {
    homeControl.pageError();
  }

  @override
  Future<CategoryEntity> loadNetwork() {
    return ApiRepository.instance.getCategoriesAsync();
  }

  @override
  localSave(CategoryEntity entity) async {
    var sp = await SharedPreferences.getInstance();
    sp.setString(CacheConstants.KEY_HOME_CACHE, json.encode(entity));
  }

  @override
  Map<String, dynamic> toJson(CategoryEntity entity) {
    return entity.toJson();
  }
}
