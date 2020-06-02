/// @author jv.lee
/// @date 2020/5/28
/// @description
class TimeTools {
  //分与毫秒的倍数
  static const int MIN = 60000;

  //时与毫秒的倍数
  static const int HOUR = 3600000;

  //天与毫秒的倍数
  static const int DAY = 86400000;

  /// 获取时间自定义显示方法
  static String getChineseTimeString(String time) {
    var oldDate = DateTime.parse(time);
    var newDate = DateTime.now();
    var oldTime = oldDate.millisecondsSinceEpoch;
    var newTime = newDate.millisecondsSinceEpoch - oldTime;
    if (newTime < MIN) {
      return "刚刚";
    } else if (newTime <= HOUR) {
      return "${newTime ~/ MIN} 分钟";
    } else if (oldDate.year == newDate.year &&
        oldDate.month == newDate.month &&
        oldDate.day == newDate.day) {
      return "${oldDate.hour} : ${oldDate.second}";
    } else if (newTime <= (DAY * 2)) {
      return "昨天";
    } else if (newTime <= (DAY * 7)) {
      return "星期${DateTime.parse(time).weekday}";
    } else {
      return "${oldDate.year}-${oldDate.month}-${oldDate.day}";
    }
  }
}
