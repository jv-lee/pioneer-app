import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/theme/widget_strings.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';

/// @author jv.lee
/// @date 2020/5/13
/// @description 扩展ListView 可添加头部Widget/底部Widget - 页面加载装 loading/error/empty/data - item加载状态 loading/error/mepty/noMore
/// 2为 page展位组件在header之下. 非切换page-data状态切换组件. 而是listBuilder中默认组件.
class SuperListView2 extends StatefulWidget {
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
  final bool isLoadMore;

  SuperListView2(
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
    return SuperListView2State();
  }
}

class SuperListView2State extends State<SuperListView2> {
  PageStatus _pageStatus;
  ItemStatus _itemStatus;
  ScrollController _controller;
  final int pageCount = 1;
  final int itemMoreCount = 1;
  double _itemHeight = 48.0;
  double fontSize = ThemeDimens.font_size_small;

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
    return buildPageData(context);
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
      margin: EdgeInsets.only(top: 100, bottom: 100),
      child: Center(
        child: CircularProgressIndicator(),
      ),
    );
  }

  Widget buildPageEmpty(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 100, bottom: 100),
      child: Center(
        child: Text(WidgetStrings.STATUS_PAGE_EMPTY,
            style: TextStyle(
                color: Theme.of(context).primaryColor, fontSize: fontSize)),
      ),
    );
  }

  Widget buildPageError(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 100, bottom: 100),
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
                if (widget.onPageReload != null) {
                  widget.statusController.pageLoading().itemEmpty();
                  widget.onPageReload();
                }
              },
            )
          ],
        ),
      ),
    );
  }

  Widget buildData(BuildContext context) {
    return Container();
  }

  Widget buildPageData(BuildContext context) {
    return ListView.builder(
        controller: _controller,
        padding: EdgeInsets.all(0),
        itemCount: widget.itemCount +
            widget.headerChildren.length +
            widget.footerChildren.length +
            pageCount +
            itemMoreCount,
        itemBuilder: (BuildContext context, int index) {
          //创建headerItem
          if (index < widget.headerChildren.length) {
            return widget.headerChildren[index];
          }

          if (index < (widget.headerChildren.length + pageCount)) {
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
                return buildData(context);
              default:
                return buildPageLoading(context);
            }
          }

          //创建contentItem
          if (index <
              (widget.headerChildren.length + pageCount + widget.itemCount)) {
            return widget.itemBuilder(
                context, index - widget.headerChildren.length - pageCount);
          }

          //创建footerItem
          if (index <
              (widget.headerChildren.length +
                  pageCount +
                  widget.itemCount +
                  widget.footerChildren.length)) {
            return widget.footerChildren[index -
                (widget.headerChildren.length + pageCount + widget.itemCount)];
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
              child: Text(WidgetStrings.STATUS_ITEM_LOADING,
                  style: TextStyle(
                      color: Theme.of(context).primaryColor,
                      fontSize: fontSize)),
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
        child: Text(WidgetStrings.STATUS_ITEM_NOT_MORE,
            style: TextStyle(
                color: Theme.of(context).primaryColor, fontSize: fontSize)),
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
            Text(
              WidgetStrings.STATUS_ITEM_ERROR,
              style: TextStyle(
                  color: Theme.of(context).primaryColor, fontSize: fontSize),
            ),
            GestureDetector(
              child: Container(
                padding: EdgeInsets.only(left: 10),
                child: Text(
                  WidgetStrings.STATUS_ITEM_RELOAD,
                  style: TextStyle(color: Colors.blue, fontSize: fontSize),
                ),
              ),
              onTapDown: (details) {
                if (widget.onItemReload != null) {
                  widget.statusController.itemLoading();
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
