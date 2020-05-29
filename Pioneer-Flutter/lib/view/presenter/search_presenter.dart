import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/widget/load/page_load.dart';

/// @author jv.lee
/// @date 2020/5/29
/// @description
class SearchPresenter {
  Future<List<ContentData>> searchDataList(
      String text, page, PageLoad pageLoad) async {
    if (text.isEmpty) return null;
    ContentEntity contentEntity = await ApiRepository.instance
        .getSearchDataAsync(text, "All", "All", page, 20);
    if (contentEntity != null) {
      pageLoad.pageTotal = contentEntity.pageCount;
      return contentEntity.data;
    }
    return null;
  }
}
