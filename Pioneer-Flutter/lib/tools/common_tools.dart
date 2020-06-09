/// @author jv.lee
/// @date 2020/5/28
/// @description
class CommonTools {
  static int totalToPage(int totalCount, int pageSize) {
    if (totalCount % pageSize != 0) {
      return totalCount ~/ (pageSize + 1);
    } else {
      return totalCount ~/ pageSize;
    }
  }
}
