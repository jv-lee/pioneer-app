import 'package:flutter/material.dart';
import 'package:pioneer_flutter/view/widget/status/status.dart';

/// @author jv.lee
/// @date 2020/5/14
/// @description
class StatusController extends ChangeNotifier {
  StatusController({
    this.pageStatus = PageStatus.data,
    this.itemStatus = ItemStatus.empty,
  });

  PageStatus pageStatus;
  ItemStatus itemStatus;

  pageLoading() {
    this.pageStatus = PageStatus.loading;
    notifyListeners();
  }

  pageError() {
    this.pageStatus = PageStatus.error;
    notifyListeners();
  }

  pageEmpty() {
    this.pageStatus = PageStatus.empty;
    notifyListeners();
  }

  pageComplete() {
    this.pageStatus = PageStatus.data;
    notifyListeners();
  }

  itemLoading() {
    this.itemStatus = ItemStatus.loading;
    notifyListeners();
  }

  itemComplete() {
    this.itemStatus = ItemStatus.noMore;
    notifyListeners();
  }

  itemError() {
    this.itemStatus = ItemStatus.error;
    notifyListeners();
  }

  itemEmpty() {
    this.itemStatus = ItemStatus.empty;
    notifyListeners();
  }
}
