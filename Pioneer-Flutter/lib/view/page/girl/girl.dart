import 'package:flutter/material.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/view/control/girl_control.dart';
import 'package:pioneer_flutter/view/item/girl_item.dart';
import 'package:pioneer_flutter/view/page/girl/girl_header.dart';
import 'package:pioneer_flutter/view/page/girl/girl_statusbar.dart';
import 'package:pioneer_flutter/view/presenter/girl_presenter.dart';
import 'package:pioneer_flutter/view/widget/load/page_load2.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';
import 'package:pioneer_flutter/view/widget/status/super_list_view.dart';

/// @author jv.lee
/// @date 2020/4/30
/// @description 主页-妹子TAB
class GirlPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _GirlState();
  }
}

class _GirlState extends State<GirlPage>
    with AutomaticKeepAliveClientMixin
    implements GirlControl {
  GirlPresenter _presenter;
  StatusController _statusController;
  ScrollController _scrollController;
  PageLoad2<ContentData> _pageLoad;

  @override
  bindData(ContentEntity data) {
    _pageLoad.pageTotal = data.pageCount;
    _pageLoad.loadData(data.data);
  }

  @override
  pageError() {
    _statusController.pageError();
  }

  @override
  void initState() {
    super.initState();
    _presenter = GirlPresenter(this);
    _scrollController = ScrollController();
    _statusController = StatusController(
        pageStatus: PageStatus.loading, itemStatus: ItemStatus.empty);
    _pageLoad = PageLoad2<ContentData>(
        data: List<ContentData>(),
        initPage: 1,
        notify: () {
          setState(() {});
        },
        statusController: _statusController);
    _presenter.getContentData(_pageLoad.getPage(false));
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return Stack(
      children: <Widget>[
        RefreshIndicator(
          onRefresh: () async {
            await Future.delayed(Duration(milliseconds: 500),
                () => _presenter.getContentData(_pageLoad.getPage(false)));
          },
          child: SuperListView(
            scrollController: _scrollController,
            statusController: _statusController,
            itemCount: _pageLoad.data.length,
            onPageReload: () {
              _presenter.getContentData(
                  _pageLoad.getPage(false));
            },
            onItemReload: () {
              _presenter.getContentData(
                  _pageLoad.getPage(true));
            },
            onLoadMore: () {
              _presenter.getContentData(
                  _pageLoad.getPage(true));
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
