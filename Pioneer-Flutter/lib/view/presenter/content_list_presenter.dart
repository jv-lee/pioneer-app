import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/widget/load/page_load.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
class ContentListPresenter {

  Future<List<ContentData>> getContentDataAsync(
      type, page, PageLoad pageLoad) async {
    ContentEntity contentEntity =
        await ApiRepository.instance.getContentDataAsync("All", type, page, 20);
    if (contentEntity != null) {
      pageLoad.pageTotal = contentEntity.pageCount;
      return contentEntity.data;
    }
    return null;
  }
}
