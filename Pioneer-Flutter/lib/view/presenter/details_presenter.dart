import 'package:pioneer_flutter/db/dao/history_dao.dart';
import 'package:pioneer_flutter/db/entity/history.dart';
import 'package:pioneer_flutter/model/content_data.dart';

/// @author jv.lee
/// @date 2020/6/9
/// @description
class DetailsPresenter {
  HistoryDao _dao;

  DetailsPresenter() {
    _dao = HistoryDao();
  }

  insertHistoryToDB(ContentData data) async {
    var isCollect = await _dao.isCollect(data.sId);
    _dao.insertHistory(History.contentToHistory(isCollect, data));
  }

  collectContent(ContentData data) async {
    var isCollect = await _dao.isCollect(data.sId);
    if (isCollect == 1) {
      print("已收藏该内容");
      return;
    }
    //添加收藏
    var dataList = await _dao.queryHistoryById(data.sId);
    var history = dataList[0];
    history.isCollect = 1;
    _dao.insertHistory(history);
    print("收藏成功");
  }

}
