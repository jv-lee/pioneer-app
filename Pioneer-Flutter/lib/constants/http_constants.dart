/// @author jv.lee
/// @date 2020/5/19
/// @description
class HttpConstants {
  static final String baseUrl = "https://gank.io/api/v2/";
  static final String pathBanner = "";

  static getCropImagePath(path) => "$path/crop/1/w/300";

  static getDetailsUri(id) => "https://gank.io/post/$id";

  static getNoneHeaderJs() =>
      "javascript:(function(){document.getElementsByClassName('header')[0].style.display = 'none'})()";
}
