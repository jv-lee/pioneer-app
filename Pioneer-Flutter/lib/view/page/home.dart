import 'package:flutter/material.dart';

/**
 * @author jv.lee
 * @description 主页-主页面
 */
class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomeState();
  }
}

class HomeState extends State<HomePage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.red,
      child: Center(
        child: Text('this is HomePage.'),
      ),
    );
  }
}
