import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/db/entity/history.dart';
import 'package:pioneer_flutter/tools/page_load.dart';
import 'package:pioneer_flutter/view/control/collect_control.dart';
import 'package:pioneer_flutter/view/item/content_child_item.dart';
import 'package:pioneer_flutter/view/presenter/collect_presenter.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';
import 'package:pioneer_flutter/view/widget/status/super_list_view2.dart';

/// @author jv.lee
/// @date 2020/6/9
/// @description
class CollectContent extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _CollectContentState();
  }
}

class _CollectContentState extends State<CollectContent>
    implements CollectControl {
  CollectPresenter _presenter;
  PageLoad<History> _pageLoad;
  StatusController _statusController;

  @override
  void initState() {
    super.initState();
    _presenter = CollectPresenter(this);
    _statusController = StatusController(
        pageStatus: PageStatus.loading, itemStatus: ItemStatus.empty);
    _pageLoad = PageLoad<History>(
        data: List<History>(),
        initPage: 0,
        notify: () {
          setState(() {});
        },
        statusController: _statusController);
    _presenter.loadHistory(_pageLoad.getPage(false));
  }

  @override
  bindData(List<History> data) {
    _pageLoad.pageTotal = _presenter.pageCount;
    _pageLoad.loadData(data);
  }

  @override
  bindError(Exception e) {
    _statusController.pageError().itemEmpty();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 1),
      child: SuperListView2(
        statusController: _statusController,
        itemCount: _pageLoad.data.length,
        onPageReload: () {
          _presenter.loadHistory(_pageLoad.getPage(false));
        },
        onItemReload: () {
          _presenter.loadHistory(_pageLoad.getPage(true));
        },
        onLoadMore: () {
          _presenter.loadHistory(_pageLoad.getPage(true));
        },
        isLoadMore: true,
        itemBuilder: (BuildContext context, int index) {
          return ContentChildItem(_pageLoad.data[index]);
        },
      ),
    );
  }
}
