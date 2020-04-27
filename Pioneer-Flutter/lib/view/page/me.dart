import 'package:flutter/material.dart';

/**
 * @author jv.lee
 * @description 主页-我的页面
 */
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
    return Container(
      color: Colors.yellow,
      child: Center(
        child: Text('this is MePage.'),
      ),
    );
  }
}
