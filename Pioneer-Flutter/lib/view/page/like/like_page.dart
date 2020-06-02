import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

/// @author jv.lee
/// @date 2020/6/2
/// @description
class LikePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _LikePageState();
  }
}

class _LikePageState extends State<LikePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(ThemeStrings.ME_ITEM_LIKE),
          centerTitle: true,
        ),
        body: Container(
          child: Center(
            child: Text(
              "没有数据.",
              style: TextStyle(color: Theme.of(context).accentColor),
            ),
          ),
        ));
  }
}
