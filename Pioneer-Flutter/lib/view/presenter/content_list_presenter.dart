import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/control/content_list_control.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
class ContentListPresenter {
  ContentListPresenter(this.control);

  final ContentListControl control;

  getContentDataAsync(type, page) {
    ApiRepository.instance
        .getContentDataAsync("All", type, page, 20)
        .then((value) {
      control.bindData(value);
    });
  }
}
