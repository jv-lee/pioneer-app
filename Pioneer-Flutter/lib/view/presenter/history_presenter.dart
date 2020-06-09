import 'package:pioneer_flutter/db/dao/history_dao.dart';
import 'package:pioneer_flutter/tools/common_tools.dart';
import 'package:pioneer_flutter/view/control/history_control.dart';

/// @author jv.lee
/// @date 2020/6/9
/// @description
class HistoryPresenter {
  HistoryControl _control;
  HistoryDao _dao;
  int pageCount = -1;

  HistoryPresenter(this._control) {
    _dao = HistoryDao();
  }

  initPageCount() async {
    if (pageCount != -1) {
      return pageCount;
    } else {
      return CommonTools.totalToPage(await _dao.queryHistoryCount(), 20);
    }
  }

  loadHistory(page) async {
    pageCount = await initPageCount();
    var data = await _dao.queryHistoryAll(page);
    _control.bindData(data);
  }
}
