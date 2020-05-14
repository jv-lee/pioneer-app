import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';

/// @author jv.lee
/// @date 2020/5/14
/// @description
class PageLoad<T> {
  List<T> data;
  int page;
  int pageTotal;
  int _currentPage;
  PageLoadFunction<T> requestData;
  Function notify;
  StatusController statusController;

  PageLoad(
      {this.data,
      this.page,
      this.pageTotal,
      this.requestData,
      this.notify,
      this.statusController});

  loadData(bool isMore) async {
    //首页加载失败 重试
    if (statusController.pageStatus == PageStatus.error) {
      statusController.pageLoading();
    }

    //item加载失败 重试
    if (statusController.itemStatus == ItemStatus.error) {
      statusController.itemLoading();
    }

    //获取数据
    var data =
        await requestData(!isMore ? _currentPage = page : ++_currentPage);

    print('loadData - isMore:$isMore currentPage:$_currentPage');

    //首页page - 数据返回空 错误加载
    if (_currentPage == page && data == null) {
      print('首页page - 数据返回空 错误加载');
      statusController.pageError();
      return;
    }

    //首页page - 数据返回空数组 空加载
    if (_currentPage == page && data.isEmpty) {
      print('首页page - 数据返回空数组 空加载');
      statusController.pageEmpty();
      return;
    }

    //首页数据加载成功
    if (_currentPage == page && data != null) {
      print('首页数据加载成功');
      this.data.clear();
      this.data.addAll(data);
      statusController.pageComplete();

      //是否只有一页数据
      if (_currentPage == pageTotal) {
        print('是否只有一页数据');
        statusController.itemComplete();
      } else {
        statusController.itemLoading();
      }
      notify();
      return;
    }

    //加载更多成功
    if (_currentPage < pageTotal && data != null) {
      print('加载更多成功');
      this.data.addAll(data);
      notify();
      statusController.itemLoading();
      return;
    }

    //加载更多失败
    if (_currentPage < pageTotal && data == null) {
      print('加载更多失败');
      _currentPage--;
      statusController.itemError();
      return;
    }

    //加载更多至尾页
    if (_currentPage == pageTotal && data != null) {
      print('加载更多至尾页');
      this.data.addAll(data);
      notify();
      statusController.itemComplete();
      return;
    }
  }
}

typedef PageLoadFunction<T> = Future<List<T>> Function(int page);
typedef PageResponseFunction<T> = void Function(List<T> data);
