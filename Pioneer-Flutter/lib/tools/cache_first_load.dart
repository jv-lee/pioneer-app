/// @author jv.lee
/// @date 2020/6/4
/// @description 缓存加载工具类
abstract class CacheFirstLoad<T> {
  bool isFirst = true;

  load() async {
    try {
      T cacheEntity;
      if (isFirst) {
        String value = await loadCache();
        if (value != null) {
          cacheEntity = buildEntity(value);
          bindData(cacheEntity);
        }
        isFirst = false;
      }

      var response = await loadNetwork();
      if (cacheEntity == null && response == null) {
        loadError();
      } else if (response != null &&   cacheEntity == null || toJson(response) != toJson(cacheEntity) ) {
        bindData(response);
        localSave(response);
      }
    } catch (e) {
      loadError();
    }
  }

  Future<String> loadCache();

  T buildEntity(String value);

  bindData(T entity);

  Future<T> loadNetwork();

  loadError();

  localSave(T entity);

  Map<String, dynamic> toJson(T entity);
}
