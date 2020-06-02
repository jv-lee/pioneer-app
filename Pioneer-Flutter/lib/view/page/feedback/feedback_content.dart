import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:pioneer_flutter/tools/toast_tools.dart';
import 'package:toast/toast.dart';

/// @author jv.lee
/// @date 2020/6/2
/// @description
class FeedbackContent extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _FeedbackContentState();
  }
}

class _FeedbackContentState extends State<FeedbackContent> {
  var contentValue = "";
  var linkValue = "";

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.max,
      children: <Widget>[
        Container(
            height: 160,
            margin: EdgeInsets.only(
                top: ThemeDimens.padding_item,
                left: ThemeDimens.padding_item,
                right: ThemeDimens.padding_item),
            child: TextField(
                onChanged: (value) {
                  contentValue = value;
                },
                maxLines: 6,
                style: TextStyle(color: Theme.of(context).accentColor),
                decoration: InputDecoration(
                  hintStyle: TextStyle(
                      color: Theme.of(context).accentColor,
                      fontSize: ThemeDimens.font_size_small),
                  hintText: ThemeStrings.FEEDBACK_HINT,
                  border: InputBorder.none,
                  contentPadding: EdgeInsets.all(ThemeDimens.padding_item),
                )),
            decoration: BoxDecoration(
                color: Theme.of(context).canvasColor,
                borderRadius: BorderRadius.all(
                    Radius.circular(ThemeDimens.common_radius)))),
        Container(
            height: 36,
            margin: EdgeInsets.only(
                top: ThemeDimens.padding_item,
                left: ThemeDimens.padding_item,
                right: ThemeDimens.padding_item),
            child: TextField(
                onChanged: (value) {
                  linkValue = value;
                },
                textAlign: TextAlign.start,
                maxLines: 1,
                keyboardType: TextInputType.number,
                style: TextStyle(color: Theme.of(context).accentColor),
                decoration: InputDecoration(
                  hintStyle: TextStyle(
                      color: Theme.of(context).accentColor,
                      fontSize: ThemeDimens.font_size_small),
                  hintText: ThemeStrings.FEEDBACK_HINT2,
                  border: InputBorder.none,
                  contentPadding: EdgeInsets.only(
                      left: ThemeDimens.padding_item,
                      right: ThemeDimens.padding_item,
                      bottom: 11),
                )),
            decoration: BoxDecoration(
                color: Theme.of(context).canvasColor,
                borderRadius: BorderRadius.all(
                    Radius.circular(ThemeDimens.common_radius)))),
        GestureDetector(
          child: Container(
            width: 1000,
            height: 36,
            alignment: Alignment.center,
            margin: EdgeInsets.only(
                top: ThemeDimens.padding_item,
                left: ThemeDimens.padding_item,
                right: ThemeDimens.padding_item),
            child: Text(
              ThemeStrings.FEEDBACK_COMMIT,
              style: TextStyle(color: Theme.of(context).primaryColor),
            ),
            decoration: BoxDecoration(
                color: Theme.of(context).accentColor,
                borderRadius: BorderRadius.all(
                    Radius.circular(ThemeDimens.common_radius))),
          ),
          onTap: () {
            if (contentValue.isEmpty || linkValue.isEmpty) {
              ToastTools.show(context, ThemeStrings.FEEDBACK_EMPTY);
              return;
            }
            ToastTools.show(context, ThemeStrings.FEEDBACK_SUCCESS);
            Navigator.pop(context);
          },
        )
      ],
    );
  }
}
