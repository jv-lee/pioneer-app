package com.lee.library.mvvm.live

import com.lee.library.mvvm.base.BaseLiveData
import kotlinx.coroutines.CoroutineScope

/**
 * @author jv.lee
 * @date 2020/5/20
 * @description
 */
class PageLiveData<T>(val limit: Int = 0) : BaseLiveData<T>() {

    var page = limit
    private var firstCache = true

    fun pageLaunch(
        isLoadMore: Boolean = false,
        isReload: Boolean = false,
        isInit: Boolean = false,
        startBlock: suspend CoroutineScope.() -> T? = { null },
        resumeBlock: suspend CoroutineScope.(Int, Int) -> T? = { _: Int, _: Int -> null },
        completedBlock: suspend CoroutineScope.(T) -> Unit = {}
    ) {
        launch {
            var response: T? = null
            //状态为加载更多且不是重新加载 增加page页码加载一下页数据
            if (isLoadMore && !isReload) {
                page++
                //非加载更多且不是初始化状态 状态为刷新 设置初始page
            } else if (!isLoadMore && !isInit) {
                page = limit
                //是否为初始化 数据不为空 则为重新构建view 重用数据 返回
            } else if (isInit && value != null) {
                return@launch
            }
            //首次加载缓存数据
            if (firstCache) {
                firstCache = false
                response = startBlock()?.also {
                    value = it
                }
            }

            //网络数据设置
            response = resumeBlock(page, limit).also {
                if (response != it) {
                    value = it
                }
            }

            //首页将网络数据设置缓存
            if (page == limit) {
                response?.run {
                    completedBlock(this)
                }
            }
        }
    }

}