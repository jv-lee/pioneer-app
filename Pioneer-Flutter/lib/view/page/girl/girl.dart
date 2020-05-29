import 'package:flutter/material.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/view/item/girl_item.dart';
import 'package:pioneer_flutter/view/page/girl/girl_header.dart';
import 'package:pioneer_flutter/view/page/girl/girl_statusbar.dart';
import 'package:pioneer_flutter/view/presenter/girl_presenter.dart';
import 'package:pioneer_flutter/view/widget/load/page_load.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';
import 'package:pioneer_flutter/view/widget/status/super_list_view.dart';

/// @author jv.lee
/// @date 2020/4/30
/// @description 主页-妹子TAB
class GirlPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return GirlState();
  }
}

class GirlState extends State<GirlPage> with AutomaticKeepAliveClientMixin {
  GirlPresenter _presenter;
  StatusController _statusController;
  ScrollController _scrollController;
  PageLoad<ContentData> _pageLoad;

  @override
  void initState() {
    super.initState();
    _presenter = GirlPresenter();
    _scrollController = ScrollController();
    _statusController = StatusController(
        pageStatus: PageStatus.loading, itemStatus: ItemStatus.empty);
    _pageLoad = PageLoad<ContentData>(
        data: List<ContentData>(),
        page: 1,
        requestData: (page) {
          return _presenter.getContentDataAsync(page, _pageLoad);
        },
        notify: () {
          setState(() {});
        },
        statusController: _statusController);
    _pageLoad.loadData(false);
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return Stack(
      children: <Widget>[
        RefreshIndicator(
          onRefresh: () async {
            await Future.delayed(
                Duration(milliseconds: 500), () => _pageLoad.loadData(false));
          },
          child: SuperListView(
            scrollController: _scrollController,
            statusController: _statusController,
            itemCount: _pageLoad.data.length,
            onPageReload: () {
              _pageLoad.loadData(false);
            },
            onItemReload: () {
              _pageLoad.loadData(true);
            },
            onLoadMore: () {
              _pageLoad.loadData(true);
            },
            isLoadMore: true,
            headerChildren: <Widget>[GirlHeader()],
            itemBuilder: (context, index) {
              return GirlItem(_pageLoad.data[index]);
            },
          ),
        ),
        GirlStatusBar(_scrollController)
      ],
    );
  }

  @override
  bool get wantKeepAlive => true;
}
