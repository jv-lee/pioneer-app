package com.lee.pioneer.model.repository

import com.google.gson.reflect.TypeToken
import com.lee.library.cache.CacheManager
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.PageData

/**
 * @author jv.lee
 * @date 2020/4/14
 * @description
 */
class CacheRepository {

    companion object {
        @Volatile
        private var instance: CacheRepository? = null

        @JvmStatic
        fun get() = instance ?: synchronized(this) {
            instance ?: CacheRepository().also { instance = it }
        }
    }

    /**
     * @param key 存储key
     * 无法完全使用泛形解析 所以使用PageData包装类配合泛形使用
     */
    fun <T> getPageCache(key: String): PageData<T>? {
        val type = object : TypeToken<PageData<T>>() {}.type
        return CacheManager.getInstance().get(key, type)
    }

    /**
     * @param key 存储key
     * @param data 存储数据源
     */
    fun <T> putPageCache(key: String, data: PageData<T>) {
        CacheManager.getInstance().put(key, data)
    }

}