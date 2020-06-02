import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:path_provider/path_provider.dart';
import 'package:pioneer_flutter/tools/toast_tools.dart';

/// @author jv.lee
/// @date 2020/6/2
/// @description
class CacheTools {
  ///加载缓存
  static Future<String> loadCache() async {
    try {
      Directory tempDir = await getTemporaryDirectory();
      double value = await _getTotalSizeOfFilesInDir(tempDir);
      print('临时目录大小: ' + value.toString());
      return _renderSize(value);
    } catch (err) {
      print(err);
    }
  }

  /// 递归方式 计算文件的大小
  static Future<double> _getTotalSizeOfFilesInDir(final FileSystemEntity file) async {
    try {
      if (file is File) {
        int length = await file.length();
        return double.parse(length.toString());
      }
      if (file is Directory) {
        final List<FileSystemEntity> children = file.listSync();
        double total = 0;
        if (children != null)
          for (final FileSystemEntity child in children)
            total += await _getTotalSizeOfFilesInDir(child);
        return total;
      }
      return 0;
    } catch (e) {
      print(e);
      return 0;
    }
  }

  ///清除缓存
  static Future<Null> clearCache(BuildContext context) async {
    //此处展示加载loading
    try {
      Directory tempDir = await getTemporaryDirectory();
      //删除缓存目录
      await _delDir(tempDir);
      ToastTools.show(context, "清除缓存成功");
    } catch (e) {
      print(e);
      ToastTools.show(context, "清除缓存失败");
    } finally {
      //此处隐藏加载loading
    }
  }

  ///递归方式删除目录
  static Future<Null> _delDir(FileSystemEntity file) async {
    try {
      if (file is Directory) {
        final List<FileSystemEntity> children = file.listSync();
        for (final FileSystemEntity child in children) {
          await _delDir(child);
        }
      }
      await file.delete();
    } catch (e) {
      print(e);
    }
  }

  ///格式化文件大小
  static _renderSize(double value) {
    if (null == value) {
      return 0;
    }
    List<String> unitArr = List()..add('B')..add('KB')..add('MB')..add('GB');
    int index = 0;
    while (value > 1024) {
      index++;
      value = value / 1024;
    }
    String size = value.toStringAsFixed(2);
    return size + unitArr[index];
  }
}
