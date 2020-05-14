import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
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

  PageStatus _status = PageStatus.error;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return SuperListView(
      pageStatus: _status,
      itemStatus: ItemStatus.loading,
      itemCount: 30,
      onPageReload: (){
        setState(() {
          this._status = PageStatus.data;
        });
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
