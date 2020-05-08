import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';

class ContentTextItem extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(ThemeDimens.padding_item),
      color: Theme.of(context).canvasColor,
      child: Column(
        children: <Widget>[
          Flex(
            direction: Axis.horizontal,
            children: <Widget>[
              Expanded(
                flex: 0,
                child: Text(
                  'ahtorName',
                  style: TextStyle(
                      fontSize: ThemeDimens.font_size_small,
                      color: Theme.of(context).primaryColorDark),
                ),
              ),
              Spacer(
                flex: 1,
              ),
              Expanded(
                flex: 0,
                child: Text(
                  'address',
                  style: TextStyle(
                      fontSize: ThemeDimens.font_size_small,
                      color: Theme.of(context).primaryColorDark),
                ),
              )
            ],
          )
        ],
      ),
    );
  }
}
