import 'dart:async';

import 'package:flutter/material.dart';
import 'package:pioneer_flutter/constants/http_constants.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/theme/theme_icons.dart';
import 'package:pioneer_flutter/theme/theme_strings.dart';
import 'package:pioneer_flutter/view/presenter/details_presenter.dart';
import 'package:pioneer_flutter/view/widget/load_progress.dart';
import 'package:share/share.dart';
import 'package:webview_flutter/webview_flutter.dart';

/// @author jv.lee
/// @date 2020/5/29
/// @description
class ContentDetailsPage extends StatefulWidget {
  ContentDetailsPage({this.data}) : super();
  final ContentData data;

  @override
  State<StatefulWidget> createState() {
    return _ContentDetailsPageState();
  }
}

class _ContentDetailsPageState extends State<ContentDetailsPage>
    with SingleTickerProviderStateMixin {
  Completer<WebViewController> _webController;
  AnimationController _animationController;
  DetailsPresenter _presenter;

  @override
  void initState() {
    super.initState();
    _webController = Completer<WebViewController>();
    _animationController = AnimationController(
        duration: Duration(milliseconds: 1000), vsync: this);
    _presenter = DetailsPresenter();
    _presenter.insertHistoryToDB(widget.data);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        title: Text(
          ThemeStrings.DETAILS_TITLE,
        ),
        bottom: PreferredSize(
            child: LoadProgress(_animationController),
            preferredSize: Size.fromHeight(2.0)),
        actions: <Widget>[
          PopupMenuButton(
            onSelected: (value) {
              if (value == 'collect') {
                _presenter.collectContent(widget.data);
              } else if (value == 'share') {
                Share.share(HttpConstants.getDetailsUri(widget.data.sId));
              }
            },
            color: Theme.of(context).canvasColor,
            offset: Offset(10, kToolbarHeight),
            itemBuilder: (BuildContext context) {
              return <PopupMenuItem<String>>[
                PopupMenuItem<String>(
                  height: 36,
                  child: Row(
                    children: <Widget>[
                      Icon(
                        ThemeIcons.favorite,
                        color: Theme.of(context).accentColor,
                      ),
                      Text(
                        ThemeStrings.MENU_COLLECT,
                        style: TextStyle(color: Theme.of(context).accentColor),
                      )
                    ],
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  ),
                  value: "collect",
                ),
                PopupMenuItem<String>(
                  height: 36,
                  child: Row(
                    children: <Widget>[
                      Icon(
                        ThemeIcons.share,
                        color: Theme.of(context).accentColor,
                      ),
                      Text(ThemeStrings.MENU_SHARED,
                          style:
                              TextStyle(color: Theme.of(context).accentColor))
                    ],
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  ),
                  value: "share",
                ),
              ];
            },
          )
        ],
      ),
      body: Builder(
        builder: (BuildContext context) {
          return WebView(
            initialUrl: HttpConstants.getDetailsUri(widget.data.sId),
            javascriptMode: JavascriptMode.unrestricted,
            onWebViewCreated: (WebViewController webViewController) {
              _webController.complete(webViewController);
            },
            onPageStarted: (url) {
              _animationController.forward();
            },
            onPageFinished: (url) {
              _webController.future.then((web) {
                web.loadUrl(HttpConstants.getNoneHeaderJs());
              });
              _animationController.forward();
            },
          );
        },
      ),
    );
  }
}
