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

class RecommendState extends State<RecommendPage>
    with AutomaticKeepAliveClientMixin {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        Expanded(
          flex: 0,
          child: RecommendToolbar(context),
        ),
        Expanded(
          child: Container(
            margin: EdgeInsets.only(top: 1),
            child: RecommendContent(),
          ),
        )
      ],
    );
  }

  @override
  bool get wantKeepAlive => true;
}
