import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';

class SuperListView extends StatefulWidget {
  final PageStatus pageStatus;
  final ItemStatus itemStatus;
  final int itemCount;
  final Function onPageReload;
  final Function onItemReload;
  final IndexedWidgetBuilder itemBuilder;
  final Widget pageLoading;
  final Widget pageEmpty;
  final Widget pageError;
  final Widget itemLoading;
  final Widget itemError;
  final Widget itemNoMore;
  final List<Widget> headerChildren;
  final List<Widget> footerChildren;
  final int itemMoreCount = 1;

  SuperListView(
      {this.pageStatus = PageStatus.data,
      this.itemStatus = ItemStatus.empty,
      this.itemCount = 0,
      this.onPageReload,
      this.onItemReload,
      this.itemBuilder,
      this.pageLoading,
      this.pageEmpty,
      this.pageError,
      this.itemLoading,
      this.itemError,
      this.itemNoMore,
      this.headerChildren = const <Widget>[],
      this.footerChildren = const <Widget>[]})
      : super();

  @override
  State<StatefulWidget> createState() {
    return SuperListViewState();
  }
}

class SuperListViewState extends State<SuperListView> {
  @override
  Widget build(BuildContext context) {
    return buildPageWidget(context);
  }

  Widget buildPageWidget(BuildContext context) {
    switch (widget.pageStatus) {
      case PageStatus.loading:
        return widget.pageLoading == null
            ? buildPageLoading(context)
            : widget.pageLoading;
      case PageStatus.empty:
        return widget.pageEmpty == null
            ? buildPageEmpty(context)
            : widget.pageEmpty;
      case PageStatus.error:
        return widget.pageError == null
            ? buildPageError(context)
            : widget.pageError;
      case PageStatus.data:
        return buildPageData(context);
      default:
        return buildPageLoading(context);
    }
  }

  Widget buildPageLoading(BuildContext context) {
    return Container(
      child: Center(
        child: CircularProgressIndicator(),
      ),
    );
  }

  Widget buildPageEmpty(BuildContext context) {
    return Container(
      child: Center(
        child: Text('data is empty'),
      ),
    );
  }

  Widget buildPageError(BuildContext context) {
    return Container(
      child: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('data is error'),
            CupertinoButton(
              child: Text('Reload'),
              onPressed: () {
                if (widget.onPageReload != null) {
                  widget.onPageReload();
                }
              },
            )
          ],
        ),
      ),
    );
  }

  Widget buildPageData(BuildContext context) {
    return ListView.builder(
        padding: EdgeInsets.all(0),
        itemCount: widget.itemCount +
            widget.headerChildren.length +
            widget.footerChildren.length +
            widget.itemMoreCount,
        itemBuilder: (BuildContext context, int index) {
          //创建headerItem
          if (index < widget.headerChildren.length) {
            return widget.headerChildren[index];
          }

          //创建contentItem
          if (index < (widget.headerChildren.length + widget.itemCount)) {
            return widget.itemBuilder(
                context, index - widget.headerChildren.length);
          }

          //创建footerItem
          if (index <
              (widget.headerChildren.length +
                  widget.itemCount +
                  widget.footerChildren.length)) {
            return widget.footerChildren[
                index - (widget.headerChildren.length + widget.itemCount)];
          }

          //创建loadMoreItem
          return buildItemWidget(context);
        });
  }

  Widget buildItemWidget(BuildContext context) {
    switch (widget.itemStatus) {
      case ItemStatus.loading:
        return buildItemLoading(context);
      case ItemStatus.empty:
        return buildItemEmpty(context);
      case ItemStatus.error:
        return buildItemError(context);
      case ItemStatus.noMore:
        return buildItemNoMore(context);
      default:
        return buildItemEmpty(context);
    }
  }

  Widget buildItemLoading(BuildContext context) {
    return Container(
      child: Center(
        child: CircularProgressIndicator(),
      ),
    );
  }

  Widget buildItemEmpty(BuildContext context) {
    return Container();
  }

  Widget buildItemNoMore(BuildContext context) {
    return Container(
      child: Text('没有更多了'),
    );
  }

  Widget buildItemError(BuildContext context) {
    return Container(
      child: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('data is error'),
            CupertinoButton(
              child: Text('Reload'),
              onPressed: () {
                if (widget.onItemReload != null) {
                  widget.onItemReload();
                }
              },
            )
          ],
        ),
      ),
    );
  }
}
