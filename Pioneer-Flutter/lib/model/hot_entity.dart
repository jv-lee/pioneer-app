import 'content_data.dart';

/// @author jv.lee
/// @date 2020/5/12
/// @description
class HotEntity {
  String category;
  List<ContentData> data;
  String hot;
  int status;

  HotEntity({this.category, this.data, this.hot, this.status});

  HotEntity.fromJson(Map<String, dynamic> json) {
    category = json['category'];
    if (json['data'] != null) {
      data = new List<ContentData>();
      json['data'].forEach((v) {
        data.add(new ContentData.fromJson(v));
      });
    }
    hot = json['hot'];
    status = json['status'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['category'] = this.category;
    if (this.data != null) {
      data['data'] = this.data.map((v) => v.toJson()).toList();
    }
    data['hot'] = this.hot;
    data['status'] = this.status;
    return data;
  }
}