import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/tools/status_tools.dart';

/// @author jv.lee
/// @date 2020/5/20
/// @description
class GirlHeader extends StatefulWidget {
  GirlHeader({Key key}) : super(key: key);

  @override
  _GirlHeaderState createState() => _GirlHeaderState();
}

class _GirlHeaderState extends State<GirlHeader> {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.only(top: StatusTools.getStatusHeight()),
      margin: EdgeInsets.fromLTRB(16, 8, 16, 8),
      child: Column(
        children: <Widget>[
          Row(
            children: <Widget>[
              Text(
                '4月14日',
                style: TextStyle(
                    color: Theme.of(context).primaryColorDark,
                    fontSize: ThemeDimens.font_size_small),
              ),
              Text('星期二',
                  style: TextStyle(
                      color: Theme.of(context).primaryColorDark,
                      fontSize: ThemeDimens.font_size_small))
            ],
          ),
          Container(
            alignment: Alignment.centerLeft,
            child: Text('Today',
                style: TextStyle(
                    fontWeight: FontWeight.bold,
                    color: Theme.of(context).accentColor,
                    fontSize: ThemeDimens.font_size_large_xx)),
          ),
        ],
      ),
    );
  }
}
