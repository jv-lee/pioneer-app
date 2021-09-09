package com.lee.pioneer.library.common.model.repository

import com.google.gson.reflect.TypeToken
import com.lee.library.cache.CacheManager
import kotlinx.coroutines.CompletableDeferred


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
     * @param key 缓存key
     */
    suspend inline fun <reified T> getCache(key: String): T? {
        val type = object : TypeToken<T>() {}.type
        return CompletableDeferred(CacheManager.getDefault().get<T>(key, type)).await()
    }

    /**
     * @param key 存储key
     * @param data 存储数据源
     */
    fun <T> putCache(key: String, data: T) {
        CacheManager.getDefault().put(key, data)
    }

}