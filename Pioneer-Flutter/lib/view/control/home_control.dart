import 'package:pioneer_flutter/model/category_entity.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
abstract class HomeControl {
  bindCategoryTabs(List<CategoryData> call);

  pageError();
}
