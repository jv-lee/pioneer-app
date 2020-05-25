import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/control/home_control.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
class HomePresenter {
  HomePresenter(this.homeControl) : super();
  final HomeControl homeControl;

  buildCategoryTabs() {
    ApiRepository.instance.getCategoriesAsync().then((value) {
      if (value == null) {
        homeControl.pageError();
      } else {
        homeControl.bindCategoryTabs(value.data);
      }
    }).catchError((error) {
      homeControl.pageError();
    });
  }
}
