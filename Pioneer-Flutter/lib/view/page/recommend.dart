import 'package:flutter/material.dart';

/**
 * @author jv.lee
 * @description 主页-推荐页面
 */
class RecommendPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return RecommendState();
  }
}

class RecommendState extends State<RecommendPage> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.grey,
      child: Center(
        child: Text('this is RecommendPage.'),
      ),
    );
  }
}
