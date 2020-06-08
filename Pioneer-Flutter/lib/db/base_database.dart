import 'package:path/path.dart';
import 'package:pioneer_flutter/db/entity/history.dart';
import 'package:sqflite/sqflite.dart';

/// @author jv.lee
/// @date 2020/6/8
/// @description 数据库操作类
class BaseDatabase {
  static const _DB_VERSION = 1;
  static const _DB_NAME = "pioneer_database.db";

  factory BaseDatabase() => _getInstance();

  static BaseDatabase get instance => _getInstance();
  static BaseDatabase _instance;

  Future<Database> _database;

  BaseDatabase._internal();

  static BaseDatabase _getInstance() {
    if (_instance == null) {
      _instance = BaseDatabase._internal();
    }
    return _instance;
  }

  init() async {
    _database = openDatabase(join(await getDatabasesPath(), _DB_NAME),
        onCreate: (db, version) {
      return db.execute(History.TABLE_EXECUTE);
    }, version: _DB_VERSION);
  }

  getDataBase() {
    if (_database == null) {
      throw Exception("database未初始化完成或未调用 BaseDatabase.instance.init();");
    }
    return _database;
  }

}
