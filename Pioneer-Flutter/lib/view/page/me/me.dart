import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/page/me/me_content.dart';
import 'package:pioneer_flutter/view/page/me/me_toolbar.dart';

/// @author jv.lee
/// @date 2020/4/30
/// @description 主页-MeTAB
class MePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _MeState();
  }
}

class _MeState extends State<MePage> with AutomaticKeepAliveClientMixin {
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
          child: MeToolbar(context),
        ),
        Expanded(
          child: MeContent(),
        )
      ],
    );
  }

  @override
  bool get wantKeepAlive => true;
}
