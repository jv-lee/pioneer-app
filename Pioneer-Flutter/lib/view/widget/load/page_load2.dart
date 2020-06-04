import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';

/// @author jv.lee
/// @date 2020/5/14
/// @description
class PageLoad2<T> {
  List<T> data;
  int initPage;
  int pageTotal;
  int _page;
  Function notify;
  StatusController statusController;

  PageLoad2(
      {this.data,
      this.initPage,
      this.pageTotal,
      this.notify,
      this.statusController});

  getPage(bool isMore) {
    return isMore ? ++_page : _page = initPage;
  }

  loadData(List<T> value) {
    //首页page - 数据返回空 错误加载
    if (_page == initPage && value == null) {
      statusController.pageError();
      return;
    }

    //首页page - 数据返回空数组 空加载
    if (_page == initPage && value.isEmpty) {
      statusController.pageEmpty();
      return;
    }

    //首页数据加载成功
    if (_page == initPage && value != null) {
      this.data.clear();
      this.data.addAll(value);
      statusController.pageComplete();

      //是否只有一页数据
      if (_page == pageTotal) {
        statusController.itemComplete();
      } else {
        statusController.itemLoading();
      }
      notify();
      return;
    }

    //加载更多成功
    if (_page < pageTotal && value != null) {
      this.data.addAll(value);
      notify();
      statusController.itemLoading();
      return;
    }

    //加载更多失败
    if (_page < pageTotal && value == null) {
      _page--;
      statusController.itemError();
      return;
    }

    //加载更多至尾页
    if (_page == pageTotal && value != null) {
      this.data.addAll(value);
      notify();
      statusController.itemComplete();
      return;
    }
  }
}

typedef PageLoadFunction<T> = Future<List<T>> Function(int page);
typedef PageResponseFunction<T> = void Function(List<T> data);
