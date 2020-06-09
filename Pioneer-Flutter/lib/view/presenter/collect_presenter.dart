import 'package:pioneer_flutter/db/dao/history_dao.dart';
import 'package:pioneer_flutter/tools/common_tools.dart';
import 'package:pioneer_flutter/view/control/collect_control.dart';

/// @author jv.lee
/// @date 2020/6/9
/// @description
class CollectPresenter {
  CollectControl _control;
  HistoryDao _dao;
  int pageCount = -1;

  CollectPresenter(this._control) {
    _dao = HistoryDao();
  }

  initPageCount() async {
    if (pageCount != -1) {
      return pageCount;
    } else {
      return CommonTools.totalToPage(await _dao.queryCollectCount(), 20);
    }
  }

  loadHistory(page) async {
    pageCount = await initPageCount();
    var data = await _dao.queryCollectAll(page);
    _control.bindData(data);
  }
}
