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

    requestData(!isMore ? _currentPage = page : ++_currentPage).then((value) {
      //首页page - 数据返回空 错误加载
      if (_currentPage == page && value == null) {
        statusController.pageError();
        return;
      }

      //首页page - 数据返回空数组 空加载
      if (_currentPage == page && value.isEmpty) {
        statusController.pageEmpty();
        return;
      }

      //首页数据加载成功
      if (_currentPage == page && value != null) {
        this.data.clear();
        this.data.addAll(value);
        statusController.pageComplete();

        //是否只有一页数据
        if (_currentPage == pageTotal) {
          statusController.itemComplete();
        } else {
          statusController.itemLoading();
        }
        notify();
        return;
      }

      //加载更多成功
      if (_currentPage < pageTotal && value != null) {
        this.data.addAll(value);
        notify();
        statusController.itemLoading();
        return;
      }

      //加载更多失败
      if (_currentPage < pageTotal && value == null) {
        _currentPage--;
        statusController.itemError();
        return;
      }

      //加载更多至尾页
      if (_currentPage == pageTotal && value != null) {
        this.data.addAll(value);
        notify();
        statusController.itemComplete();
        return;
      }
    }).catchError((error) {
      print('content list load error :$error');
      //首页page - 数据返回空 错误加载
      if (_currentPage == page) {
        statusController.pageError();
        return;
      }

      //加载更多失败
      if (_currentPage < pageTotal) {
        _currentPage--;
        statusController.itemError();
        return;
      }
    });
  }
}

typedef PageLoadFunction<T> = Future<List<T>> Function(int page);
typedef PageResponseFunction<T> = void Function(List<T> data);
