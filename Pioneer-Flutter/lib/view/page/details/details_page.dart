import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/constants/http_constants.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:webview_flutter/webview_flutter.dart';

/// @author jv.lee
/// @date 2020/5/29
/// @description
class ContentDetailsPage extends StatefulWidget {
  ContentDetailsPage({this.data}) : super();
  final ContentData data;

  @override
  State<StatefulWidget> createState() {
    return ContentDetailsPageState();
  }
}

class ContentDetailsPageState extends State<ContentDetailsPage> {
  final Completer<WebViewController> _controller =
      Completer<WebViewController>();

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text(ThemeStrings.DETAILS_TITLE),
        bottom: PreferredSize(
            child: LinearProgressIndicator(
              value: 50,
              backgroundColor: Theme.of(context).primaryColor,

            ),
            preferredSize: Size.fromHeight(3.0)),
      ),
      body: Builder(
        builder: (BuildContext context) {
          return WebView(
            initialUrl: HttpConstants.getDetailsUri(widget.data.sId),
            javascriptMode: JavascriptMode.unrestricted,
            onWebViewCreated: (WebViewController webViewController) {
              _controller.complete(webViewController);
            },
            onPageFinished: (url) {
              _controller.future.then((web) {
                web.loadUrl(HttpConstants.getNoneHeaderJs());
              });
            },
          );
        },
      ),
    );
  }
}
