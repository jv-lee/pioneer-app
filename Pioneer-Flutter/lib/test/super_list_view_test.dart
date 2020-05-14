import 'package:flutter/material.dart';
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
  List<String> data = List<String>();

  int page = 1;

  loadData(int page) {
    Future.delayed(Duration(seconds: 3), () {
      setState(() {
        for (var i = 0; i < 10; i++) {
          data.add("item - index-$i");
        }
        if (page == 1) {
          _statusController.pageComplete().itemLoading();
        } else if (page == 5) {
          _statusController.itemComplete();
        } else {
          _statusController.itemLoading();
        }
      });
    });
  }

  @override
  void initState() {
    super.initState();
    _statusController = StatusController(
        pageStatus: PageStatus.loading, itemStatus: ItemStatus.empty);
    loadData(page);
  }

  @override
  Widget build(BuildContext context) {
    return SuperListView(
      statusController: _statusController,
      itemCount: data.length,
      onPageReload: () {
        _statusController.pageComplete().itemError();
      },
      onItemReload: () {
        _statusController.itemComplete();
      },
      onLoadMore: () {
        loadData(++page);
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
        Container(
          color: Colors.green,
          height: 30,
        ),
        Container(
          color: Colors.pink,
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
            child: Text(data[index]),
          ),
        );
      },
    );
  }
}
