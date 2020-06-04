import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/control/girl_control.dart';

/// @author jv.lee
/// @date 2020/5/19
/// @description
class GirlPresenter {
  GirlPresenter(this.control) : super();
  final GirlControl control;

  getContentData(page) {
    ApiRepository.instance
        .getContentDataAsync("Girl", "Girl", page, 20)
        .then((value) {
      control.bindData(value);
    }).catchError((error) {
      control.pageError();
    });
  }
}
