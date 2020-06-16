import 'package:flutter/material.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/view/control/content_list_control.dart';
import 'package:pioneer_flutter/view/item/content_multiple_item.dart';
import 'package:pioneer_flutter/view/item/content_single_item.dart';
import 'package:pioneer_flutter/view/item/content_text_item.dart';
import 'package:pioneer_flutter/view/presenter/content_list_presenter.dart';
import 'package:pioneer_flutter/tools/page_load.dart';
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

class _HomeContentListState extends State<HomeContentList>
    with AutomaticKeepAliveClientMixin
    implements ContentListControl {
  ContentListPresenter _presenter;
  StatusController _statusController;
  PageLoad<ContentData> _pageLoad;

  @override
  bindData(ContentEntity data) {
    if(data != null) {
      _pageLoad.pageTotal = data.pageCount;
      _pageLoad.loadData(data.data);
    }else{
      _pageLoad.loadData(null);
    }
  }

  @override
  pageError() {
    _statusController.pageError();
  }

  @override
  void initState() {
    super.initState();
    _presenter = ContentListPresenter(this);
    _statusController = StatusController(
        pageStatus: PageStatus.loading, itemStatus: ItemStatus.empty);
    _pageLoad = PageLoad<ContentData>(
        data: List<ContentData>(),
        initPage: 1,
        notify: () {
          setState(() {});
        },
        statusController: _statusController);
    Future.delayed(
        Duration(milliseconds: widget.type == "Android" ? 0 : 500),
        () => _presenter.getContentDataAsync(
            widget.type, _pageLoad.getPage(false)));
  }

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return RefreshIndicator(
      onRefresh: () async {
        await Future.delayed(
            Duration(milliseconds: 500),
            () => _presenter.getContentDataAsync(
                widget.type, _pageLoad.getPage(false)));
      },
      child: SuperListView(
        statusController: _statusController,
        itemCount: _pageLoad.data.length,
        onPageReload: () {
          _presenter.getContentDataAsync(widget.type, _pageLoad.getPage(false));
        },
        onItemReload: () {
          _presenter.getContentDataAsync(widget.type, _pageLoad.getPage(true));
        },
        onLoadMore: () {
          _presenter.getContentDataAsync(widget.type, _pageLoad.getPage(true));
        },
        isLoadMore: true,
        itemBuilder: (BuildContext context, int index) {
          var entity = _pageLoad.data[index];
          if (entity.images.length == 0) {
            return ContentTextItem(entity);
          } else if (entity.images.length == 1) {
            return ContentSingleItem(entity);
          } else {
            return ContentMultipleItem(entity);
          }
        },
      ),
    );
  }

  @override
  bool get wantKeepAlive => true;
}
