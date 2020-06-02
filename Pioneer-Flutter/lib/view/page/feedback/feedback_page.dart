import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:pioneer_flutter/view/page/feedback/feedback_content.dart';

/// @author jv.lee
/// @date 2020/6/2
/// @description
class FeedbackPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _FeedbackPageState();
  }
}

class _FeedbackPageState extends State<FeedbackPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(ThemeStrings.ME_ITEM_FEEDBACK),
        centerTitle: true,
      ),
      body: FeedbackContent(),
    );
  }
}
