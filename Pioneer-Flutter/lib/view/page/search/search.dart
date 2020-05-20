import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/search_field.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 搜索页面
class SearchPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return SearchPageState();
  }
}

class SearchPageState extends State<SearchPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: SearchField(),
        centerTitle: true,
        automaticallyImplyLeading: false, //没有返回键
      ),
      body: Container(
        color: Theme.of(context).backgroundColor,
      ),
    );
  }
}
