package com.lee.library.mvvm

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * @author jv.lee
 * @date 2019-08-15
 * @description
 */
open class ResponsePageViewModel(application: Application, val firstPage: Int = 0) :
    BaseViewModel(application) {

    var page = firstPage
    private var firstCache = true

    /**
     * 分页数据加载封装
     */
    fun <T> pageLaunch(
        isLoadMore: Boolean,
        isReload: Boolean = false,
        cacheBlock: suspend CoroutineScope.() -> Unit,
        networkBlock: suspend CoroutineScope.() -> T?,
        completedBlock: suspend CoroutineScope.(T) -> Unit = {}
    ) {
        launch(-1) {
            //加载更多设置page
            if (isLoadMore) {
                if (!isReload) page++
            } else {
                page = firstPage
            }
            //设置首次缓存flag
            if (firstCache) {
                firstCache = false
                cacheBlock()
            }
            //根据页码回调网络请求及是否调用 completedBlock函数体 (completedBlock处理只使用一次的缓存存储,及数据设置)
            if (page == firstPage) {
                val response = networkBlock()
                response?.let {
                    completedBlock(response)
                }
            } else {
                networkBlock()
            }
        }
    }

    fun <T> cacheLaunch(
        cacheBlock: suspend CoroutineScope.() -> Unit,
        networkBlock: suspend CoroutineScope.() -> T,
        completedBlock: suspend CoroutineScope.(T) -> Unit
    ) {
        pageLaunch(
            isLoadMore = false,
            isReload = false,
            cacheBlock = cacheBlock,
            networkBlock = networkBlock,
            completedBlock = completedBlock
        )
    }

    /**
     * 获取网络数据返回非空判断
     */
    suspend fun <T> executeResponseAny(response: T?, successBlock: (T) -> Unit) {
        coroutineScope {
            when (response) {
                null -> {
                    throw Exception("response is null")
                }
                else -> {
                    successBlock(response)
                }
            }
        }
    }

}

