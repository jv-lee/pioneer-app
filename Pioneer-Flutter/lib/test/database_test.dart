import 'package:flutter/material.dart';
import 'package:pioneer_flutter/db/dao/history_dao.dart';
import 'package:pioneer_flutter/db/entity/history.dart';

/// @author jv.lee
/// @date 2020/6/8
/// @description
class DatabaseTestPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _DatabaseTestState();
  }
}

class _DatabaseTestState extends State<DatabaseTestPage> {
  int count = 0;
  int page = 0;
  HistoryDao dao;

  @override
  initState() {
    super.initState();
    dao = HistoryDao();
  }

  insert() {
    for (int i = 0; i < 100; i++) {
      dao.insertHistory(History(
          id: i.toString(),
          readTime: DateTime.now().millisecondsSinceEpoch,
          isCollect: 0,
          title: "title - $i",
          description: "description - $i",
          viewCount: 0,
          likeCount: 0));
    }
  }

  query() {
    dao.queryHistoryAll(page++).then((value) {
      print(value.toString());
    });
  }

  queryCount() {
    dao.queryHistoryCount().then((value) {
      print(value);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 100),
      color: Colors.white,
      child: Center(
        child: Column(
          children: <Widget>[
            MaterialButton(
              child: Text('添加数据'),
              onPressed: () {
                insert();
              },
            ),
            MaterialButton(
              child: Text('查询所有数据'),
              onPressed: () {
                query();
              },
            ),
            MaterialButton(
              child: Text('查询所有数据count'),
              onPressed: () {
                queryCount();
              },
            ),
          ],
        ),
      ),
    );
  }
}
