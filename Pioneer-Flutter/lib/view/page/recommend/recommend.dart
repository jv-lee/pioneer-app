import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/page/recommend/recommend_content.dart';
import 'package:pioneer_flutter/view/page/recommend/recommend_toolbar.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-推荐TAB
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
    return Scaffold(
        appBar: RecommendToolbar(context), body: RecommendContent());
  }
}
