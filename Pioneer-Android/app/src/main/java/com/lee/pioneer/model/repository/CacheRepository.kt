package com.lee.pioneer.model.repository

import com.google.gson.reflect.TypeToken
import com.lee.library.cache.CacheManager
import com.lee.pioneer.model.entity.Category
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.PageData
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

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
     * 获取分类tab 缓存数据
     */
    fun getCategoryCacheAsync(key: String): Deferred<PageData<Category>?> {
        val type = object : TypeToken<PageData<Category>>() {}.type
        return CompletableDeferred(CacheManager.getInstance().get<PageData<Category>>(key, type))
    }

    /**
     * 获取Content列表缓存
     * @param key 存储key
     * 无法完全使用泛形解析 所以使用PageData包装类配合泛形使用
     */
    fun getContentCacheAsync(
        key: String
    ): Deferred<PageData<Content>?> {
        val type = object : TypeToken<PageData<Content>>() {}.type
        return CompletableDeferred(CacheManager.getInstance().get<PageData<Content>>(key, type))
    }

    /**
     * @param key 存储key
     * @param data 存储数据源
     */
    fun <T> putCache(key: String, data: T) {
        CacheManager.getInstance().put(key, data)
    }

}