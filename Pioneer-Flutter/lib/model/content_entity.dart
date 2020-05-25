import 'content_data.dart';

/// @author jv.lee
/// @date 2020/5/19
/// @description
class ContentEntity {
  List<ContentData> data;
  int page;
  int pageCount;
  int status;
  int totalCounts;

  ContentEntity(
      {this.data, this.page, this.pageCount, this.status, this.totalCounts});

  ContentEntity.fromJson(Map<String, dynamic> json) {
    if (json['data'] != null) {
      data = new List<ContentData>();
      json['data'].forEach((v) {
        data.add(new ContentData.fromJson(v));
      });
    }
    page = json['page'];
    pageCount = json['page_count'];
    status = json['status'];
    totalCounts = json['total_counts'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.data != null) {
      data['data'] = this.data.map((v) => v.toJson()).toList();
    }
    data['page'] = this.page;
    data['page_count'] = this.pageCount;
    data['status'] = this.status;
    data['total_counts'] = this.totalCounts;
    return data;
  }
}
