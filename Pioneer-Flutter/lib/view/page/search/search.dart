import 'package:flutter/material.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/view/item/content_multiple_item.dart';
import 'package:pioneer_flutter/view/item/content_single_item.dart';
import 'package:pioneer_flutter/view/item/content_text_item.dart';
import 'package:pioneer_flutter/view/presenter/search_presenter.dart';
import 'package:pioneer_flutter/view/widget/load/page_load.dart';
import 'package:pioneer_flutter/view/widget/search_field.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';
import 'package:pioneer_flutter/view/widget/status/super_list_view.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 搜索页面
class SearchPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _SearchPageState();
  }
}

class _SearchPageState extends State<SearchPage> {
  SearchPresenter _presenter;
  StatusController _statusController;
  PageLoad _pageLoad;
  var searchText = "";

  @override
  void initState() {
    super.initState();
    _presenter = SearchPresenter();
    _statusController = StatusController(
        pageStatus: PageStatus.data, itemStatus: ItemStatus.empty);
    _pageLoad = PageLoad<ContentData>(
        data: List<ContentData>(),
        page: 1,
        requestData: (page) {
          return _presenter.searchDataList(searchText, page, _pageLoad);
        },
        notify: () {
          setState(() {});
        },
        statusController: _statusController);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: SearchField(
          onSubmitted: (value) {
            searchText = value;
            _statusController.pageLoading();
            _pageLoad.loadData(false);
          },
        ),
        centerTitle: true,
        automaticallyImplyLeading: false, //没有返回键
      ),
      body: Container(
        margin: EdgeInsets.only(top: 1),
        child: SuperListView(
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
              return ContentTextItem(entity);
            } else if (entity.images.length == 1) {
              return ContentSingleItem(entity);
            } else {
              return ContentMultipleItem(entity);
            }
          },
        ),
      ),
    );
  }
}
