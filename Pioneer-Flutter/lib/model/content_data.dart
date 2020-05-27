/// @author jv.lee
/// @date 2020/5/25
/// @description 
class ContentData {
  String sId;
  String author;
  String category;
  String createdAt;
  String desc;
  List<String> images;
  int likeCounts;
  String publishedAt;
  int stars;
  String title;
  String type;
  String url;
  int views;

  ContentData(
      {this.sId,
        this.author,
        this.category,
        this.createdAt,
        this.desc,
        this.images,
        this.likeCounts,
        this.publishedAt,
        this.stars,
        this.title,
        this.type,
        this.url,
        this.views});

  ContentData.fromJson(Map<String, dynamic> json) {
    sId = json['_id'];
    author = json['author'];
    category = json['category'];
    createdAt = json['createdAt'];
    desc = json['desc'];
    images = json['images'].cast<String>();
    likeCounts = json['likeCounts'];
    publishedAt = json['publishedAt'];
    stars = json['stars'];
    title = json['title'];
    type = json['type'];
    url = json['url'];
    views = json['views'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['_id'] = this.sId;
    data['author'] = this.author;
    data['category'] = this.category;
    data['createdAt'] = this.createdAt;
    data['desc'] = this.desc;
    data['images'] = this.images;
    data['likeCounts'] = this.likeCounts;
    data['publishedAt'] = this.publishedAt;
    data['stars'] = this.stars;
    data['title'] = this.title;
    data['type'] = this.type;
    data['url'] = this.url;
    data['views'] = this.views;
    return data;
  }
}