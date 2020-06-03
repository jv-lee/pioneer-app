import 'package:flutter/material.dart';
import 'package:pioneer_flutter/constants/recommend_constants.dart';
import 'package:pioneer_flutter/model/banner_entity.dart';
import 'package:pioneer_flutter/model/content_data.dart';
import 'package:pioneer_flutter/view/control/recommend_control.dart';
import 'package:pioneer_flutter/view/item/content_multiple_item.dart';
import 'package:pioneer_flutter/view/item/content_single_item.dart';
import 'package:pioneer_flutter/view/item/content_text_item.dart';
import 'package:pioneer_flutter/view/page/recommend/recommend_content_banner.dart';
import 'package:pioneer_flutter/view/page/recommend/recommend_content_tab.dart';
import 'package:pioneer_flutter/view/presenter/recommend_presenter.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_controller.dart';
import 'package:pioneer_flutter/view/widget/status/super_list_view2.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-推荐TAB-内容组件
class RecommendContent extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _RecommendContentState();
  }
}

class _RecommendContentState extends State<RecommendContent>
    implements RecommendControl {
  List<ContentData> contentData = List<ContentData>();
  List<BannerData> bannerData = List<BannerData>();
  StatusController _statusController;
  ScrollController _scrollController;
  RecommendPresenter _presenter;
  String type = RecommendConstants.KEY_VIEWS;

  @override
  void initState() {
    super.initState();
    _presenter = RecommendPresenter(this);
    _statusController = StatusController(
        pageStatus: PageStatus.loading, itemStatus: ItemStatus.empty);
    _scrollController = ScrollController();
    _presenter.getBannerDate();
    _presenter.getContentData(type);
  }

  @override
  bindBanner(List<BannerData> call) {
    setState(() {
      bannerData.addAll(call);
    });
  }

  @override
  bindContent(List<ContentData> call) {
    contentData.clear();
    if (call.length == 0) {
      _statusController.pageEmpty().itemEmpty();
    } else {
      contentData.addAll(call);
      _statusController.pageComplete().itemComplete();
    }
    setState(() {});
  }

  @override
  pageError() {
    _statusController.pageError();
  }

  Widget buildList(BuildContext context) {
    return SuperListView2(
      isLoadMore: false,
      statusController: _statusController,
      scrollController: _scrollController,
      itemCount: contentData.length,
      onPageReload: () {
        _presenter.getContentData(type);
      },
      headerChildren: <Widget>[
        RecommendContentBanner(
          data: bannerData,
        ),
        RecommendContentTab((value) {
          type = value;
          _statusController.pageLoading();
          _presenter.getContentData(type);
        })
      ],
      itemBuilder: (BuildContext context, int index) {
        var entity = contentData[index];
        if (entity.images.length == 0) {
          return ContentTextItem(entity);
        } else if (entity.images.length == 1) {
          return ContentSingleItem(entity);
        } else {
          return ContentMultipleItem(entity);
        }
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return buildList(context);
  }
}
