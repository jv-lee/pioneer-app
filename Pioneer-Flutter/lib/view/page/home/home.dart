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
  List<CategoryData> _categoryData = List();
  PageStatus _status = PageStatus.loading;

  @override
  bindData(List<CategoryData> data) {
    _categoryData.clear();
    _categoryData.addAll(data);
    if (data.length == 0) {
      _status = PageStatus.empty;
    } else {
      _status = PageStatus.data;
    }
    setState(() {});
  }

  @override
  bindError(Exception e) {
    _status = PageStatus.error;
    setState(() {});
  }

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
            status: _status,
            child: HomeContent(_categoryData),
            reLoadFun: () {
              _presenter.buildCategoryTabs();
            },
          ),
        )
      ],
    );
  }
}
