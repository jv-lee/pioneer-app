/// @author jv.lee
/// @date 2020/5/19
/// @description 
class CategoryEntity {
  List<CategoryData> data;
  int status;

  CategoryEntity({this.data, this.status});

  CategoryEntity.fromJson(Map<String, dynamic> json) {
    if (json['data'] != null) {
      data = new List<CategoryData>();
      json['data'].forEach((v) {
        data.add(new CategoryData.fromJson(v));
      });
    }
    status = json['status'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.data != null) {
      data['data'] = this.data.map((v) => v.toJson()).toList();
    }
    data['status'] = this.status;
    return data;
  }
}

class CategoryData {
  String sId;
  String coverImageUrl;
  String desc;
  String title;
  String type;

  CategoryData({this.sId, this.coverImageUrl, this.desc, this.title, this.type});

  CategoryData.fromJson(Map<String, dynamic> json) {
    sId = json['_id'];
    coverImageUrl = json['coverImageUrl'];
    desc = json['desc'];
    title = json['title'];
    type = json['type'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['_id'] = this.sId;
    data['coverImageUrl'] = this.coverImageUrl;
    data['desc'] = this.desc;
    data['title'] = this.title;
    data['type'] = this.type;
    return data;
  }
}