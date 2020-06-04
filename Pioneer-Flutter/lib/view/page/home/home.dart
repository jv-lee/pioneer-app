import 'package:flutter/material.dart';
import 'package:pioneer_flutter/model/category_entity.dart';
import 'package:pioneer_flutter/view/control/home_control.dart';
import 'package:pioneer_flutter/view/presenter/home_presenter.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';
import 'package:pioneer_flutter/view/widget/status/status_page.dart';

import 'home_content.dart';
import 'home_toolbar.dart';

/// @author jv.lee
/// @date 2020/4/30
/// @description 主页-HomeTAB
class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _HomeState();
}

class _HomeState extends State<HomePage> implements HomeControl {
  HomePresenter _presenter;
  List<CategoryData> data = List();
  PageStatus status = PageStatus.loading;

  @override
  void initState() {
    super.initState();
    _presenter = HomePresenter(this);
    _presenter.buildCategoryTabs();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        Expanded(
          flex: 0,
          child: HomeToolbar(context),
        ),
        Expanded(
          child: StatusPage(
            status: status,
            child: HomeContent(data),
            reLoadFun: () {
              _presenter.buildCategoryTabs();
            },
          ),
        )
      ],
    );
  }

  @override
  bindCategoryTabs(List<CategoryData> call) {
    data.clear();
    data.addAll(call);
    if (data.length == 0) {
      status = PageStatus.empty;
    } else {
      status = PageStatus.data;
    }
    setState(() {});
  }

  @override
  pageError() {
    status = PageStatus.error;
    setState(() {});
  }
}
