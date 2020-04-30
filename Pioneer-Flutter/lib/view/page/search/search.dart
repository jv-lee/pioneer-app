import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer/theme/theme_icons.dart';
import 'package:pioneer/theme/theme_dimens.dart';
import 'package:pioneer/theme/theme_strings.dart';
import 'package:pioneer/view/widget/search_field.dart';

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
