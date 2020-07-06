package com.lee.library.mvvm.live

import com.lee.library.mvvm.base.BaseLiveData
import com.lee.library.utils.LogUtil
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
        startBlock: suspend CoroutineScope.() -> T? = { null },
        resumeBlock: suspend CoroutineScope.(Int, Int) -> T? = { _: Int, _: Int -> null },
        completedBlock: suspend CoroutineScope.(T) -> Unit = {}
    ) {
        launch {
            var response: T? = null
            //加载更多设置page
            if (isLoadMore) {
                if (!isReload) page++
            } else {
                page = limit
            }
            //首次加载缓存数据
            if (firstCache) {
                firstCache = false
                response = startBlock()?.also {
                    value = it
                    LogUtil.i("设置首页缓存数据 page $page")
                }
            }

            //网络数据设置
            response = resumeBlock(page, limit).also {
                if (response != it) {
                    value = it
                    LogUtil.i("设置网络数据 page $page")
                }
            }

            //首页将网络数据设置缓存
            if (page == limit) {
                response?.run {
                    LogUtil.i("保存本地缓存数据 page $page")
                    completedBlock(this)
                }
            }
        }
    }

}