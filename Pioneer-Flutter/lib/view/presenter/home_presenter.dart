import 'dart:convert';

import 'package:pioneer_flutter/constants/cache_constants.dart';
import 'package:pioneer_flutter/model/category_entity.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/control/home_control.dart';
import 'package:shared_preferences/shared_preferences.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
class HomePresenter {
  HomePresenter(this.homeControl) : super();
  final HomeControl homeControl;

  buildCategoryTabs() async {
    SharedPreferences.getInstance().then((sp) {
      //获取缓存数据
      String value = sp.get(CacheConstants.KEY_HOME_CACHE);
      if (value == null) {
        return null;
      } else {
        var categoryEntity = CategoryEntity.fromJson(json.decode(value));
        print(categoryEntity.toString());
        homeControl.bindData(categoryEntity.data);
        return categoryEntity;
      }
    }).then((value) async {
      //获取网络数据
      var response = await ApiRepository.instance.getCategoriesAsync();
      if (value == null && response == null) {
        homeControl.pageError();
      } else if (value.toString() != response.toString()) {
        homeControl.bindData(response.data);
        var sp = await SharedPreferences.getInstance();
        sp.setString(CacheConstants.KEY_HOME_CACHE, json.encode(response));
      }
    }).catchError((error) {
      homeControl.pageError();
    });
  }
}
