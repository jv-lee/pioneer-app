import 'package:pioneer_flutter/model/banner_entity.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/view/control/base_control.dart';

/// @author jv.lee
/// @date 2020/5/22
/// @description
abstract class RecommendControl extends BaseControl<List<ContentData>> {
  bindBanner(List<BannerData> data);
}
