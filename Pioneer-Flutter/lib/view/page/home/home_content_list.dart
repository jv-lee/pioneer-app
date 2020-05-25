import 'package:flutter/material.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/view/item/content_multiple_item.dart';
import 'package:pioneer_flutter/view/item/content_single_item.dart';
import 'package:pioneer_flutter/view/item/content_text_item.dart';
import 'package:pioneer_flutter/view/presenter/content_list_presenter.dart';
import 'package:pioneer_flutter/view/widget/load/page_load.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';
import 'package:pioneer_flutter/view/widget/status/super_list_view.dart';

/// @author jv.lee
/// @date 2020/5/25
/// @description
class HomeContentList extends StatefulWidget {
  HomeContentList(this.type) : super();
  final String type;

  @override
  _HomeContentListState createState() => _HomeContentListState();
}

class _HomeContentListState extends State<HomeContentList> {
  ContentListPresenter _presenter;
  StatusController _statusController;
  PageLoad<ContentData> _pageLoad;

  @override
  void initState() {
    super.initState();
    _presenter = ContentListPresenter();
    _statusController = StatusController(
        pageStatus: PageStatus.loading, itemStatus: ItemStatus.empty);
    _pageLoad = PageLoad<ContentData>(
        data: List<ContentData>(),
        page: 1,
        requestData: (page) {
          return _presenter.getContentDataAsync(widget.type, page, _pageLoad);
        },
        notify: () {
          setState(() {});
        },
        statusController: _statusController);
    _pageLoad.loadData(false);
  }

  @override
  Widget build(BuildContext context) {
    return SuperListView(
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
      itemBuilder: (BuildContext context, int index) {
        var entity = _pageLoad.data[index];
        if (entity.images.length == 0) {
          return ContentTextItem(
            data: entity,
          );
        } else if (entity.images.length == 1) {
          return ContentSingleItem(
            entity,
          );
        } else {
          return ContentMultipleItem(
            entity,
          );
        }
      },
    );
  }
}
