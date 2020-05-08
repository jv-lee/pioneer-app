import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/page/recommend/recommend_content_banner.dart';

class RecommendContent extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return RecommendContentState();
  }
}

class RecommendContentState extends State<RecommendContent> {
  var _itemCount = 0;

  @override
  void initState() {
    super.initState();
    setState(() {
      _itemCount = 2;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Theme.of(context).backgroundColor,
      child: ListView.builder(
          padding: EdgeInsets.all(0),
          itemCount: _itemCount,
          itemBuilder: (BuildContext context, int index) {
            if (index == 0) {
              return RecommendContentBanner();
            }
            return Container(
              color: index % 2 == 0 ? Colors.red : Colors.greenAccent,
              child: Center(
                child: Text('index - $index'),
              ),
            );
          }),
    );
  }
}
