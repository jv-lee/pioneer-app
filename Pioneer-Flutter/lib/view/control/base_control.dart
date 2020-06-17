/// @author jv.lee
/// @date 2020/6/4
/// @description

abstract class BaseControl<T> {
  bindData(T data);

  bindError(Exception e);
}
