import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/load/page_load.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';
import 'package:pioneer_flutter/view/widget/status/super_list_view.dart';

/// @author jv.lee
/// @date 2020/5/14
/// @description  SuperListView测试案例
class SuperListViewTest extends StatefulWidget {
  SuperListViewTest({Key key}) : super(key: key);

  @override
  _SuperListViewTestState createState() => _SuperListViewTestState();
}

class _SuperListViewTestState extends State<SuperListViewTest> {
  StatusController _statusController;
  PageLoad<String> _pageLoad;

  int requestCount = 0;

  Future<List<String>> getData(page,PageLoad pageLoad) async {
    return Future.delayed(Duration(seconds: 3), () {
      pageLoad.pageTotal = 10;
      var array = List<String>();
      for (var i = 0; i < 10; i++) {
        array.add("page - $page item - index-$i");
      }
      if (page == 1) {
        if (requestCount == 0) {
          requestCount++;
          return null;
        }
        return array;
      }

      if (page == 2) {
        if (requestCount < 2) {
          requestCount++;
          return null;
        }
        return array;
      }
      return array;
    });
  }

  @override
  void initState() {
    super.initState();
    _statusController = StatusController(
        pageStatus: PageStatus.loading, itemStatus: ItemStatus.empty);
    _pageLoad = PageLoad<String>(
        data: List<String>(),
        page: 1,
        requestData: (page) {
          return getData(page,_pageLoad);
        },
        notify: () {
          setState(() {});
        },
        statusController: _statusController);
    _pageLoad.loadData(false);
  }

  @override
  Widget build(BuildContext context) {
    return SuperListView(
      statusController: _statusController,
      itemCount: _pageLoad.data.length,
      onPageReload: () {
        _pageLoad.loadData(false);
      },
      onItemReload: () {
        _pageLoad.loadData(true);
      },
      onLoadMore: () {
        _pageLoad.loadData(true);
      },
      isLoadMore: true,
      headerChildren: <Widget>[
        Container(
          color: Colors.orange,
          height: 30,
        ),
        Container(
          color: Colors.yellow,
          height: 30,
        ),
      ],
      footerChildren: <Widget>[
        Container(
          color: Colors.blue,
          height: 50,
        ),
        Container(
          color: Colors.brown,
          height: 50,
        ),
      ],
      itemBuilder: (context, index) {
        return Container(
          margin: EdgeInsets.only(bottom: 10),
          color: Colors.red,
          padding: EdgeInsets.all(30),
          child: Center(
            child: Text(_pageLoad.data[index]),
          ),
        );
      },
    );
  }
}
