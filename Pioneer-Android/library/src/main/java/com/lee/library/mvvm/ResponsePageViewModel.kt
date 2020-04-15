package com.lee.library.mvvm

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * @author jv.lee
 * @date 2019-08-15
 * @description
 */
open class ResponsePageViewModel(application: Application, var page: Int) :
    BaseViewModel(application) {

    private var firstCache = true

    /**
     * 分页数据加载封装
     */
    fun pageLaunch(
        isLoadMore: Boolean,
        cacheBlock: suspend CoroutineScope.() -> Unit,
        networkBlock: suspend CoroutineScope.() -> Unit
    ) {
        launch(-1) {
            if (isLoadMore) page++ else page = 1
            if (firstCache) {
                firstCache = false
                cacheBlock()
            }
            networkBlock()
        }
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

