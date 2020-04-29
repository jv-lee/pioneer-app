import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer/theme/theme_colors.dart';
import 'package:pioneer/theme/theme_dimens.dart';
import 'package:pioneer/view/widget/clear_thme.dart';

class HomeTab extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomeTabState();
  }
}

class HomeTabState extends State<HomeTab> with SingleTickerProviderStateMixin {
  TabController _tabController; //需要定义一个Controller
  List tabs = ["新闻", "历史", "图片"];

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: tabs.length, vsync: this);
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
        height: ThemeDimens.tab_height,
        //清空原有主题点击水波纹效果
        child: ClearTheme(
            child: TabBar(
                indicatorSize: TabBarIndicatorSize.label,
                indicatorColor: ThemeColors.colorAccent,
                unselectedLabelColor: ThemeColors.colorPrimary,
                labelColor: ThemeColors.colorAccent,
                controller: _tabController,
                tabs: tabs
                    .map((title) => Tab(
                          text: title,
                        ))
                    .toList())));
  }
}
