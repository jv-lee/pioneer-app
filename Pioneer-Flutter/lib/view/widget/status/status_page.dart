import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';

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
        child: Text('data is empty'),
      ),
    );
  }

  Widget buildError(BuildContext context) {
    return Container(
      child: Center(
        child: Row(
          children: <Widget>[
            Text('data is error'),
            CupertinoButton(
              child: Text('ReLoad'),
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
    return Container(child: Text('child == null'));
  }
}
