import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/control/search_control.dart';

/// @author jv.lee
/// @date 2020/5/29
/// @description
class SearchPresenter {
  SearchPresenter(this.control) : super();
  final SearchControl control;

  searchDataList(String text, page) {
    if (text.isEmpty) {
      control.searchEmpty();
      return;
    }
    ApiRepository.instance
        .getSearchDataAsync(text, "All", "All", page, 20)
        .then((value) {
      control.bindData(value);
    });
  }
}
