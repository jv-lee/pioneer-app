package com.lee.library.mvvm

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * @author jv.lee
 * @date 2019-08-15
 * @description
 */
open class ResponsePageViewModel(application: Application, val page: Int = 0) :
    BaseViewModel(application) {

    private var mPage = page
    private var firstCache = true

    /**
     * 分页数据加载封装
     */
    fun <T> pageLaunch(
        isLoadMore: Boolean,
        cacheBlock: suspend CoroutineScope.() -> Unit,
        networkBlock: suspend CoroutineScope.() -> T?,
        completedBlock: suspend CoroutineScope.(T) -> Unit
    ) {
        launch(-1) {
            if (isLoadMore) mPage++ else mPage = page
            if (firstCache) {
                firstCache = false
                cacheBlock()
            }
            if (mPage == page) {
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
        pageLaunch(false, cacheBlock, networkBlock, completedBlock)
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

