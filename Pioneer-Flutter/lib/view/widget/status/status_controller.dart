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

  StatusController pageLoading() {
    this.pageStatus = PageStatus.loading;
    notifyListeners();
    return this;
  }

  StatusController pageError() {
    this.pageStatus = PageStatus.error;
    notifyListeners();
    return this;
  }

  StatusController pageEmpty() {
    this.pageStatus = PageStatus.empty;
    notifyListeners();
    return this;
  }

  StatusController pageComplete() {
    this.pageStatus = PageStatus.data;
    notifyListeners();
    return this;
  }

  StatusController itemLoading() {
    this.itemStatus = ItemStatus.loading;
    notifyListeners();
    return this;
  }

  StatusController itemComplete() {
    this.itemStatus = ItemStatus.noMore;
    notifyListeners();
    return this;
  }

  StatusController itemError() {
    this.itemStatus = ItemStatus.error;
    notifyListeners();
    return this;
  }

  StatusController itemEmpty() {
    this.itemStatus = ItemStatus.empty;
    notifyListeners();
    return this;
  }

}
