import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

enum StatusPageEnum { loading, empty, error, data }

class StatusPage extends StatefulWidget {
  final StatusPageEnum status;
  final Widget child;
  final Function reLoadFun;

  StatusPage({this.child, this.status, this.reLoadFun});

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
    switch (widget.status == null ? StatusPageEnum.loading : widget.status) {
      case StatusPageEnum.loading:
        return buildLoading(context);
      case StatusPageEnum.empty:
        return buildEmpty(context);
      case StatusPageEnum.error:
        return buildError(context);
      case StatusPageEnum.data:
        return widget.child == null
            ? Container(child: Text('child == null'))
            : widget.child;
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
}
