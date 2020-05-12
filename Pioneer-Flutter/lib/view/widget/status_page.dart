import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

enum StatusPageEnum { loading, empty, error, data }

class StatusPage extends StatefulWidget {
  final StatusPageEnum status;
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
    switch (widget.status == null ? StatusPageEnum.loading : widget.status) {
      case StatusPageEnum.loading:
        return widget.loading == null ? buildLoading(context) : widget.loading;
      case StatusPageEnum.empty:
        return widget.empty == null ? buildEmpty(context) : widget.empty;
      case StatusPageEnum.error:
        return widget.error == null ? buildError(context) : widget.error;
      case StatusPageEnum.data:
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
