import 'package:pioneer_flutter/db/base_database.dart';
import 'package:sqflite/sqflite.dart';

/// @author jv.lee
/// @date 2020/6/8
/// @description 数据路记录浏览记录及收藏数据模型
class History {
  final int id;
  final int readTime;
  final int isCollect;
  final String title;
  final String description;
  final int viewCount;
  final int likeCount;

  History(
      {this.id,
      this.readTime,
      this.isCollect,
      this.title,
      this.description,
      this.viewCount,
      this.likeCount});

  static const TABLE_NAME = "history";
  static const TABLE_EXECUTE =
      "CREATE TABLE $TABLE_NAME(id INTEGER PRIMARY KEY,"
      "read_time INTEGER,"
      "is_collect INTEGER,"
      "title TEXT,"
      "description TEXT,"
      "view_count INTEGER,"
      "like_count INTEGER)";

  @override
  String toString() {
    return 'History{title: $title,time:$readTime}';
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'read_time': readTime,
      'is_collect': isCollect,
      'title': title,
      'description': description,
      'view_count': viewCount,
      'like_count': likeCount
    };
  }

}
