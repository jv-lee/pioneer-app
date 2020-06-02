import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';

/// @author jv.lee
/// @date 2020/6/2
/// @description
class HistoryPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _HistoryPageState();
  }
}

class _HistoryPageState extends State<HistoryPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(ThemeStrings.ME_ITEM_VIEWS),
        centerTitle: true,
      ),
    );
  }
}
