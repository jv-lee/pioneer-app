import 'package:dio/dio.dart';

/// @author jv.lee
/// @date 2020/5/8
/// @description
class HttpManager {
  factory HttpManager() => _getInstance();

  static HttpManager get instance => _getInstance();
  static HttpManager _instance;

  //初始化 可包含代码块
  HttpManager._internal() {
    BaseOptions options = new BaseOptions(
      baseUrl: "https://gank.io/api/v2/",
      connectTimeout: 5000,
      receiveTimeout: 3000,
    );
    _dio = new Dio(options);
  }

  static HttpManager _getInstance() {
    if (_instance == null) {
      _instance = new HttpManager._internal();
    }
    return _instance;
  }

  Dio _dio;

  Dio getDio() {
    return _dio;
  }
}
