package com.lee.pioneer.model.repository

import com.google.gson.reflect.TypeToken
import com.lee.library.cache.CacheManager

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

    fun <T> getDataCache(key: String): T {
        val type = object : TypeToken<T>() {}.type
        return CacheManager.getInstance().get<T>(key, type)
    }

    fun <T> putDataCache(key: String, data: T) {
        CacheManager.getInstance().put(key, data)
    }

}