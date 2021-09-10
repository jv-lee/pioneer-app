package com.lee.library.extensions

import com.google.gson.reflect.TypeToken
import com.lee.library.cache.CacheManager
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @param key 缓存key
 * @return 协程体
 */
suspend inline fun <reified T> CacheManager.getCache(key: String): T? {
    val type = object : TypeToken<T>() {}.type
    return CompletableDeferred(CacheManager.getDefault().get<T>(key, type)).await()
}

/**
 * @param key 缓存key
 * @return 数据流
 */
inline fun <reified T> CacheManager.getCacheFlow(key: String): Flow<T?> {
    val type = object : TypeToken<T>() {}.type
    return flow { CacheManager.getDefault().get<T>(key, type) }
}

/**
 * @param key 存储key
 * @param data 存储数据源
 */
fun <T> CacheManager.putCache(key: String, data: T) {
    CacheManager.getDefault().put(key, data)
}

