import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/model/repository/api_repository.dart';
import 'package:pioneer_flutter/view/widget/load/page_load.dart';

/// @author jv.lee
/// @date 2020/5/19
/// @description
class GirlPresenter {
  Future<List<ContentData>> getContentDataAsync(page, PageLoad pageLoad) async {
    ContentEntity contentEntity = await ApiRepository.instance
        .getContentDataAsync("Girl", "Girl", page, 20);
    if (contentEntity != null) {
      pageLoad.pageTotal = contentEntity.pageCount;
      return contentEntity.data;
    }
    return null;
  }
}
