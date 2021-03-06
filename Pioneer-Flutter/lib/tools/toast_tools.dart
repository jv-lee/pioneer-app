import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:toast/toast.dart';

/// @author jv.lee
/// @date 2020/6/2
/// @description
class ToastTools {
  static show(BuildContext context, String msg) {
    Toast.show(msg, context,
        gravity: Toast.CENTER,
        duration: Toast.LENGTH_LONG,
        backgroundColor: Theme.of(context).primaryColor,
        textColor: Theme.of(context).accentColor);
  }
}
