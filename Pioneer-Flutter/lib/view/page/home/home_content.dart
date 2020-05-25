import 'package:flutter/material.dart';
import 'package:pioneer_flutter/model/category_entity.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/view/page/home/home_content_list.dart';
import 'package:pioneer_flutter/view/widget/clear_theme.dart';
import 'package:pioneer_flutter/view/widget/temp_color_page.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description 主页-HomeTAB-内部page页面
class HomeContent extends StatefulWidget {
  HomeContent(this.data) : super();
  final List<CategoryData> data;

  @override
  State<StatefulWidget> createState() => HomeContentState();
}

class HomeContentState extends State<HomeContent>
    with SingleTickerProviderStateMixin {
  TabController _tabController; //需要定义一个Controller
  PageController _pageController;

  var currentPage = 0;
  var isPageCanChanged = true;

  @override
  void initState() {
    super.initState();
    _pageController = PageController(initialPage: 0);
    _tabController = TabController(length: widget.data.length, vsync: this);
    _tabController.addListener(() {
      //判断TabBar是否切换
      if (_tabController.indexIsChanging) {
        onPageChange(_tabController.index, p: _pageController);
      }
    });
  }

  onPageChange(int index, {PageController p, TabController t}) async {
    //判断是哪一个切换
    if (p != null) {
      isPageCanChanged = false;
      //等待PageView切换完毕,再释放PageView监听
      await _pageController.animateToPage(index,
          duration: Duration(milliseconds: 500), curve: Curves.ease);
      isPageCanChanged = true;
    } else {
      //切换TabBar
      _tabController.animateTo(index);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Flex(
      direction: Axis.vertical,
      mainAxisSize: MainAxisSize.max,
      children: <Widget>[
        Expanded(
          flex: 0,
          child: SizedBox(
              height: ThemeDimens.tab_height,
              //清空原有主题点击水波纹效果
              child: ClearTheme(
                  child: TabBar(
                      isScrollable: true,
                      indicatorSize: TabBarIndicatorSize.label,
                      indicatorColor: Theme.of(context).accentColor,
                      unselectedLabelColor: Theme.of(context).primaryColor,
                      labelColor: Theme.of(context).accentColor,
                      controller: _tabController,
                      tabs: widget.data
                          .map((item) => Tab(
                                text: item.title.toUpperCase(),
                              ))
                          .toList()))),
        ),
        Expanded(
          flex: 1,
          child: PageView.builder(
              itemCount: widget.data.length,
              onPageChanged: (index) {
                if (isPageCanChanged) {
                  onPageChange(index);
                }
              },
              controller: _pageController,
              itemBuilder: (BuildContext context, int index) {
                return HomeContentList(widget.data[index].type);
              }),
        ),
      ],
    );
  }
}
