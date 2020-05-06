import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/page/me/me_content.dart';
import 'package:pioneer_flutter/view/page/me/me_toolbar.dart';

/// @author jv.lee
/// @description 主页-我的页面
class MePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MeState();
  }
}

class MeState extends State<MePage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Flex(direction: Axis.vertical,children: <Widget>[
      Expanded(flex:0,child: MeToolbar(),),
      Expanded(flex:1,child: MeContent(),),
    ],);
  }
}
