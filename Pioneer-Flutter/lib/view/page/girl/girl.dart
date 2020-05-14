import 'package:flutter/material.dart';
import 'package:pioneer_flutter/test/super_list_view_test.dart';

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
  @override
  Widget build(BuildContext context) {
    return SuperListViewTest();
  }
}
