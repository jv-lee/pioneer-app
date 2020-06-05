import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/widget_strings.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';

/// @author jv.lee
/// @date 2020/5/12
/// @description
class StatusPage extends StatefulWidget {
  final PageStatus status;
  final Widget child;
  final Widget loading;
  final Widget empty;
  final Widget error;
  final Function reLoadFun;

  StatusPage(
      {this.status,
      this.child,
      this.loading,
      this.empty,
      this.error,
      this.reLoadFun});

  @override
  State<StatefulWidget> createState() {
    return StatusPageState();
  }
}

class StatusPageState extends State<StatusPage> {
  double fontSize = ThemeDimens.font_size_small;

  @override
  Widget build(BuildContext context) {
    return buildWidget(context);
  }

  Widget buildWidget(BuildContext context) {
    switch (widget.status == null ? PageStatus.loading : widget.status) {
      case PageStatus.loading:
        return widget.loading == null ? buildLoading(context) : widget.loading;
      case PageStatus.empty:
        return widget.empty == null ? buildEmpty(context) : widget.empty;
      case PageStatus.error:
        return widget.error == null ? buildError(context) : widget.error;
      case PageStatus.data:
        return widget.child == null ? buildData(context) : widget.child;
      default:
        return buildLoading(context);
    }
  }

  Widget buildLoading(BuildContext context) {
    return Container(
      child: Center(
        child: CircularProgressIndicator(),
      ),
    );
  }

  Widget buildEmpty(BuildContext context) {
    return Container(
      child: Center(
        child: Text(WidgetStrings.STATUS_PAGE_EMPTY,
            style: TextStyle(
                color: Theme.of(context).primaryColor, fontSize: fontSize)),
      ),
    );
  }

  Widget buildError(BuildContext context) {
    return Container(
      child: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(WidgetStrings.STATUS_PAGE_ERROR,
                style: TextStyle(
                    color: Theme.of(context).primaryColor, fontSize: fontSize)),
            CupertinoButton(
              child: Text(
                WidgetStrings.STATUS_PAGE_RELOAD,
                style: TextStyle(fontSize: fontSize),
              ),
              onPressed: () {
                if (widget.reLoadFun != null) {
                  widget.reLoadFun();
                }
              },
            )
          ],
        ),
      ),
    );
  }

  Widget buildData(BuildContext context) {
    return Container(
        child: Text('child == null',
            style: TextStyle(color: Theme.of(context).primaryColor)));
  }
}
