/// @author jv.lee
/// @date 2020/6/4
/// @description 缓存加载工具类
abstract class CacheLoad<T> {
  load() async {
    try {
      //获取缓存数据
      String value = await loadCache();
      T cacheEntity;
      if (value != null && value.isNotEmpty) {
        cacheEntity = buildEntity(value);
        bindData(cacheEntity);
      }

      var response = await loadNetwork();
      if (value == null && response == null) {
        throw Exception("cache & response is null.");
      } else if (response != null && cacheEntity == null ||
          toJson(response) != toJson(cacheEntity)) {
        bindData(response);
        localSave(response);
      }
    } catch (e) {
      loadError(e);
    }
  }

  Future<String> loadCache();

  T buildEntity(String value);

  bindData(T entity);

  Future<T> loadNetwork();

  loadError(Exception e);

  localSave(T entity);

  Map<String, dynamic> toJson(T entity);
}
