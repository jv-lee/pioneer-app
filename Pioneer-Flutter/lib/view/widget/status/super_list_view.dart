import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';

/// @author jv.lee
/// @date 2020/5/13
/// @description 扩展ListView 可添加头部Widget/底部Widget - 页面加载装 loading/error/empty/data - item加载状态 loading/error/mepty/noMore
class SuperListView extends StatefulWidget {
  final ScrollController scrollController;
  final StatusController statusController;
  final int itemCount;
  final Function onPageReload;
  final Function onItemReload;
  final Function onLoadMore;
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
  final bool isLoadMore;

  SuperListView(
      {this.scrollController,
      @required this.statusController,
      this.itemCount = 0,
      this.onPageReload,
      this.onItemReload,
      this.onLoadMore,
      this.itemBuilder,
      this.pageLoading,
      this.pageEmpty,
      this.pageError,
      this.itemLoading,
      this.itemError,
      this.itemNoMore,
      this.isLoadMore = false,
      this.headerChildren = const <Widget>[],
      this.footerChildren = const <Widget>[]})
      : super();

  @override
  State<StatefulWidget> createState() {
    return SuperListViewState();
  }
}

class SuperListViewState extends State<SuperListView> {
  PageStatus _pageStatus;
  ItemStatus _itemStatus;
  ScrollController _controller;
  double _itemHeight = 48.0;

  initScrollController() {
    if (widget.scrollController != null) {
      _controller = widget.scrollController;
    } else {
      _controller = ScrollController();
    }
    _controller.addListener(() {
      if (_controller.position.pixels == _controller.position.maxScrollExtent) {
        if (widget.statusController.itemStatus != ItemStatus.noMore &&
            widget.statusController.itemStatus != ItemStatus.error) {
          widget.onLoadMore();
        }
      }
    });
  }

  changeStatus() {
    setState(() {
      _pageStatus = widget.statusController.pageStatus;
      _itemStatus = widget.statusController.itemStatus;
    });
  }

  @override
  void initState() {
    super.initState();
    _pageStatus = widget.statusController.pageStatus;
    _itemStatus = widget.statusController.itemStatus;
    widget.statusController.addListener(changeStatus);
    initScrollController();
  }

  @override
  void dispose() {
    super.dispose();
    widget.statusController.removeListener(changeStatus);
  }

  @override
  Widget build(BuildContext context) {
    return buildPageWidget(context);
  }

  Widget buildPageWidget(BuildContext context) {
    switch (_pageStatus) {
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
        controller: _controller,
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

          return buildItemWidget(context);
        });
  }

  Widget buildItemWidget(BuildContext context) {
    switch (_itemStatus) {
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
      height: _itemHeight,
      child: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            SizedBox(
              height: 16,
              width: 16,
              child: CircularProgressIndicator(
                strokeWidth: 2,
              ),
            ),
            Container(
              child: Text('加载中...'),
              padding: EdgeInsets.only(left: 10),
            ),
          ],
        ),
      ),
    );
  }

  Widget buildItemEmpty(BuildContext context) {
    return Container(
      height: _itemHeight,
    );
  }

  Widget buildItemNoMore(BuildContext context) {
    return Container(
      height: _itemHeight,
      child: Center(
        child: Text('没有更多了'),
      ),
    );
  }

  Widget buildItemError(BuildContext context) {
    return Container(
      height: _itemHeight,
      child: Center(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('data is error'),
            GestureDetector(
              child: Container(
                padding: EdgeInsets.only(left: 10),
                child: Text(
                  'Reload',
                  style: TextStyle(color: Colors.blue),
                ),
              ),
              onTapDown: (details) {
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
