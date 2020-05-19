import 'package:flutter/material.dart';
import 'package:pioneer_flutter/constants/http_constants.dart';
import 'package:pioneer_flutter/model/content_entity.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
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

class GirlState extends State<GirlPage> {
  GirlPresenter _presenter;
  StatusController _statusController;
  PageLoad<ContentData> _pageLoad;

  @override
  void initState() {
    super.initState();
    _presenter = GirlPresenter();
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
      itemBuilder: (context, index) {
        return Card(
          margin: EdgeInsets.fromLTRB(30, 15, 30, 15),
          elevation: 10,
          child: Container(
            height: ThemeDimens.item_girl_root_height,
            child: Column(
              children: <Widget>[
                Container(
                  height: ThemeDimens.item_girl_picture_height,
                  decoration: BoxDecoration(
                      borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(
                            ThemeDimens.item_content_picture_radius),
                        topRight: Radius.circular(
                            ThemeDimens.item_content_picture_radius),
                      ),
                      image: DecorationImage(
                          image: NetworkImage(HttpConstants.getCropImagePath(_pageLoad.data[index].images[0])),
                          fit: BoxFit.cover)),
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.all(ThemeDimens.padding_large),
                  decoration: BoxDecoration(
                      color: Theme.of(context).canvasColor,
                      borderRadius: BorderRadius.only(
                        bottomLeft: Radius.circular(
                            ThemeDimens.item_content_picture_radius),
                        bottomRight: Radius.circular(
                            ThemeDimens.item_content_picture_radius),
                      )),
                  height: ThemeDimens.item_girl_des_height,
                  child: Text(
                    _pageLoad.data[index].desc,
                    maxLines: 3,
                    overflow: TextOverflow.ellipsis,
                    style: TextStyle(
                        fontSize: ThemeDimens.font_size_small,
                        color: Theme.of(context).primaryColor),
                  ),
                )
              ],
            ),
          ),
        );
      },
    );
  }
}
