import 'package:pioneer_flutter/db/base_database.dart';
import 'package:pioneer_flutter/db/entity/history.dart';
import 'package:sqflite/sqflite.dart';

/// @author jv.lee
/// @date 2020/6/8
/// @description
class HistoryDao {
  Future<void> insertHistory(History history) async {
    final Database db = await BaseDatabase.instance.getDataBase();
    db.insert(History.TABLE_NAME, history.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
  }

  Future<void> insertHistoryList(List<History> historyList) async {
    final Database db = await BaseDatabase.instance.getDataBase();
    for (var value in historyList) {
      db.insert(History.TABLE_NAME, value.toMap(),
          conflictAlgorithm: ConflictAlgorithm.replace);
    }
  }

  Future<void> deleteHistory(int id) async {
    final Database db = await BaseDatabase.instance.getDataBase();

    db.delete(
      History.TABLE_NAME,
      where: "id = ?",
      whereArgs: [id],
    );
  }

  Future<void> updateHistory(History history) async {
    final Database db = await BaseDatabase.instance.getDataBase();

    db.update(
      History.TABLE_NAME,
      history.toMap(),
      where: "id = ?",
      whereArgs: [history.id],
    );
  }

  Future<List<History>> query() async {
    final Database db = await BaseDatabase.instance.getDataBase();

    final List<Map<String, dynamic>> maps = await db.query(History.TABLE_NAME);

    return _mapsToList(maps);
  }

  ///查询所有浏览记录 获取分页数据
  Future<List<History>> queryHistoryAll(page) async {
    final Database db = await BaseDatabase.instance.getDataBase();

    final List<Map<String, dynamic>> maps = await db.rawQuery(
        "select * from ${History.TABLE_NAME} order by read_time desc limit ${page * 20},20");

    return _mapsToList(maps);
  }

  ///查询浏览记录总条数
  Future<int> queryHistoryCount() async {
    final Database db = await BaseDatabase.instance.getDataBase();

    return Sqflite.firstIntValue(
        await db.rawQuery("select count(*) from ${History.TABLE_NAME}"));
  }

  ///查询所有收藏
  Future<List<History>> queryCollectAll(page) async {
    final Database db = await BaseDatabase.instance.getDataBase();

    final List<Map<String, dynamic>> maps = await db.rawQuery(
        "select * from ${History.TABLE_NAME} WHERE is_collect = 1 limit ${page * 20},20");

    return _mapsToList(maps);
  }

  ///获取收藏内容总条数
  Future<int> queryCollectCount() async {
    final Database db = await BaseDatabase.instance.getDataBase();

    return Sqflite.firstIntValue(await db.rawQuery(
        "SELECT COUNT(*) FROM ${History.TABLE_NAME} WHERE is_collect = 1"));
  }

  ///查询该条内容是否点击收藏
  Future<int> isCollect(id) async {
    final Database db = await BaseDatabase.instance.getDataBase();

    return Sqflite.firstIntValue(await db.rawQuery(
        "SELECT COUNT(*) FROM ${History.TABLE_NAME} WHERE id = '$id' AND is_collect = 1"));
  }

  ///通过id查询一条数据
  Future<List<History>> queryHistoryById(id) async {
    final Database db = await BaseDatabase.instance.getDataBase();

    final List<Map<String, dynamic>> maps = await db
        .rawQuery("SELECT * FROM ${History.TABLE_NAME} WHERE id = '$id' ");

    return _mapsToList(maps);
  }

  Future<List<History>> _mapsToList(List<Map<String, dynamic>> maps) async {
    return List.generate(maps.length, (i) {
      return History(
          id: maps[i]['id'],
          readTime: maps[i]['read_time'],
          isCollect: maps[i]['is_collect'],
          title: maps[i]['title'],
          description: maps[i]['description'],
          viewCount: maps[i]['view_count'],
          likeCount: maps[i]['like_count']);
    });
  }
}
