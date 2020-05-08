import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/banner_indicator.dart';

typedef GetWidgetCallback = Widget Function(int index);

class BannerPage extends StatefulWidget {
  final int length;

  final GetWidgetCallback getWidget;
  final int width;
  final int height;
  final Widget selectorWidget;
  final Widget normalWidget;
  final bool autoLoop;
  final bool showIndicator;
  final bool spaceMode;

  BannerPage(
      {Key key,
      this.length,
      this.getWidget,
      @required this.width,
      @required this.height,
      this.selectorWidget,
      this.normalWidget,
      this.autoLoop = true,
      this.showIndicator = true,
      this.spaceMode = true});

  @override
  State<StatefulWidget> createState() {
    return _BannerPageState();
  }
}

class _BannerPageState extends State<BannerPage> {
  @override
  void didUpdateWidget(BannerPage oldWidget) {
    super.didUpdateWidget(oldWidget);
  }

  @override
  void deactivate() {
    super.deactivate();
  }

  @override
  void dispose() {
    super.dispose();
    if (_timer != null) {
      _timer.cancel();
    }
  }

  PageController _pageController;
  int _currentIndex = 100;
  Timer _timer;

  //设置定时器
  _setTimer() {
    if (_timer != null) {
      return;
    }

    _timer = Timer.periodic(Duration(seconds: 5), (_) {
      _pageController.animateToPage(_currentIndex + 1,
          duration: Duration(milliseconds: 400), curve: Curves.easeOut);
    });
  }

  @override
  Widget build(BuildContext context) {
    return getBanner2();
  }

  int bannerMax = 10000000000;

  double myWidth = 0;

  BannerIndicator squareIndicator;

  GlobalKey<BannerIndicatorState> indicatorKey =
      GlobalKey<BannerIndicatorState>();

  double viewportFractionCustom = 1;
  double paddingCustom = 0;

  Widget getBanner2() {
    if (widget.spaceMode) {
      viewportFractionCustom = 0.925;
      paddingCustom = 0.0125;
    }
    myWidth = MediaQuery.of(context).size.width;

    if (widget.length > 0 && _pageController == null) {
      _pageController = new PageController(
          initialPage: widget.length * 100,
          viewportFraction: viewportFractionCustom);
      _currentIndex = widget.length * 100;

      if (widget.autoLoop) {
        _setTimer();
      }
    }
    if (widget.length == 0) {
      return Container();
    }
    PageView pageView = new PageView.builder(
      itemBuilder: ((context, index) {
        GlobalKey _key = new GlobalKey();
        Container cc = Container(
          key: _key,
          //左右两个padding
          margin: EdgeInsets.only(
              left: myWidth * paddingCustom, right: myWidth * paddingCustom),
          width: myWidth,

          height: myWidth *
              (viewportFractionCustom - paddingCustom * 2) *
              widget.width /
              widget.height,
          child: widget.getWidget(index % widget.length),
        );

        return cc;
      }),
      itemCount: bannerMax,
      scrollDirection: Axis.horizontal,
      reverse: false,
      controller: _pageController,
      physics: PageScrollPhysics(parent: BouncingScrollPhysics()),
      onPageChanged: ((index) {
        _currentIndex = index;
        if (indicatorKey.currentState != null) {
          indicatorKey.currentState
              .updateWidgets(widget.length, (_currentIndex) % widget.length);
        }
      }),
    );
    if (widget.showIndicator) {
      squareIndicator = BannerIndicator(
        normalWidget: widget.normalWidget,
        selectorWidget: widget.selectorWidget,
        key: indicatorKey,
        length: widget.length,
        select: (_currentIndex) % widget.length,
      );
    }

    return Container(
      width: MediaQuery.of(context).size.width,
      height: MediaQuery.of(context).size.width *
          widget.width /
          widget.height *
          (viewportFractionCustom - paddingCustom * 2),
      child: Stack(
        children: <Widget>[pageView, _getSquareIndicator()],
      ),
    );
  }

  Widget _getSquareIndicator() {
    if (widget.showIndicator) {
      return Positioned(
        bottom: myWidth * 0.02,
        left: 0,
        right: 0,
        child: squareIndicator,
      );
    } else {
      return Container();
    }
  }
}
