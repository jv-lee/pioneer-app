import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';
import 'package:pioneer_flutter/view/widget/status/super_list_view.dart';

/// @author jv.lee
/// @date 2020/4/30
/// @description 主页-妹子TAB
class GirlPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return GirlState();
  }
}

class GirlState extends State<GirlPage> {
  StatusController _statusController;

  @override
  void initState() {
    super.initState();
    _statusController = StatusController(
        pageStatus: PageStatus.error, itemStatus: ItemStatus.empty);
  }

  @override
  Widget build(BuildContext context) {
    return SuperListView(
      statusController: _statusController,
      itemCount: 30,
      onPageReload: () {
        _statusController.pageComplete();
        _statusController.itemError();
      },
      onItemReload: () {
        _statusController.itemComplete();
      },
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
          padding: EdgeInsets.all(10),
          child: Center(
            child: Text('this is item $index'),
          ),
        );
      },
    );
  }
}
