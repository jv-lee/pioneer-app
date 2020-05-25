import 'package:pioneer_flutter/model/banner_entity.dart';
import 'package:pioneer_flutter/model/content_data.dart';

/// @author jv.lee
/// @date 2020/5/22
/// @description
abstract class RecommendControl {
  bindBanner(List<BannerData> call);

  bindContent(List<ContentData> call);

  pageError();
}
