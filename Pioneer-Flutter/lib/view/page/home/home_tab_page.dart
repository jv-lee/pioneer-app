import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/theme/theme_dimens.dart';
import 'package:pioneer_flutter/view/widget/clear_theme.dart';
import 'package:pioneer_flutter/view/widget/temp_color_page.dart';

class HomeTabPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HomeTabPageState();
  }
}

class HomeTabPageState extends State<HomeTabPage>
    with SingleTickerProviderStateMixin {
  TabController _tabController; //需要定义一个Controller
  PageController _pageController;

  var currentPage = 0;
  var isPageCanChanged = true;

  List tabs = ["新闻", "历史", "图片"];
  List pages = [TempColorPage(color: Colors.red,),TempColorPage(color: Colors.yellow,),TempColorPage(color: Colors.greenAccent,)];

  @override
  void initState() {
    super.initState();
    _pageController = PageController(initialPage: 0);
    _tabController = TabController(length: tabs.length, vsync: this);
    _tabController.addListener((){
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
      await _pageController.animateToPage(index, duration: Duration(milliseconds: 500), curve: Curves.ease);
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
                      indicatorSize: TabBarIndicatorSize.label,
                      indicatorColor: Theme.of(context).accentColor,
                      unselectedLabelColor: Theme.of(context).primaryColor,
                      labelColor: Theme.of(context).accentColor,
                      controller: _tabController,
                      tabs: tabs.map((title) => Tab(text: title,)).toList()))),
        ),
        Expanded(
          flex: 1,
          child: PageView.builder(
              itemCount: pages.length,
              onPageChanged: (index){
                if(isPageCanChanged) {
                 onPageChange(index);
                }
              },
              controller: _pageController,
              itemBuilder: (BuildContext context,int index){
                return pages[index];
              }),
        ),
      ],
    );
  }
}
