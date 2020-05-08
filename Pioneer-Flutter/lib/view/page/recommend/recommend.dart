import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/page/recommend/recommend_content.dart';
import 'package:pioneer_flutter/view/page/recommend/recommend_toolbar.dart';

/// @author jv.lee
/// @description 主页-推荐页面
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
    return Flex(
      direction: Axis.vertical,
      children: <Widget>[
        Expanded(
          flex: 0,
          child: RecommendToolbar(),
        ),
        Expanded(
          flex: 1,
          child: RecommendContent(),
        )
      ],
    );
  }
}
