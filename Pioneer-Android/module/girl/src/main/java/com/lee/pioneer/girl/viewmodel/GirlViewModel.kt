package com.lee.pioneer.girl.viewmodel

import androidx.lifecycle.viewModelScope
import com.lee.library.cache.CacheManager
import com.lee.library.extensions.*
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.mvvm.live.PageLiveData
import com.lee.library.mvvm.live.applyData
import com.lee.library.mvvm.load.LoadStatus
import com.lee.library.mvvm.load.PageNumber
import com.lee.library.utils.LogUtil
import com.lee.pioneer.girl.model.repository.ApiRepository
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_GIRL
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.library.common.entity.*
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService
import kotlinx.coroutines.flow.*

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlViewModel : BaseViewModel() {

    private val meService by lazy { ModuleService.find<MeService>() }

    private val repository by lazy { ApiRepository() }

    val contentData by lazy { PageLiveData<PageData<Content>>(limit = 1) }

    /**
     * 浏览后添加至数据库
     */
    fun insertContentHistoryToDB(content: Content) {
        launchMain {
            launchIO {
                val extends = meService.isCollect(content._id)
                meService
                    .insert(
                        ContentHistory.parse(
                            ContentType.PICTURE,
                            ContentSource.ID,
                            extends,
                            content
                        )
                    )
            }
        }
    }

    fun getGirlContentData(
        @LoadStatus status: Int
    ) {
        launchMain {
            contentData.pageLaunchFlow(status, { page: Int ->
                //获取网络数据
                repository.api
                    .getFlowContentDataAsync(CATEGORY_GIRL, CATEGORY_GIRL, page, PAGE_COUNT)
            }, {
                //首次获取首页缓存数据
                CacheManager.getDefault()
                    .getCacheFlow<PageData<Content>>(CONTENT_CACHE_KEY + CATEGORY_GIRL.lowercase())
            }, {
                //保存首页缓存
                if (contentData.page == contentData.limit) {
                    CacheManager.getDefault().putCache(CONTENT_CACHE_KEY + CATEGORY_GIRL.lowercase(), it)
                }
                //填充历史数据 让activity在重建时可以从liveData中获取到完整数据 首页无需填充原始数据(会造成数据重复)
                contentData.applyData(contentData.value?.data, it.data)
            })
        }
    }

    val contentState = PageStateFlow(1)

    val contentFlowData: StateFlow<UiState> = contentState.pageLaunchFlow(
        networkBlock = { page ->
            //获取网络分页数据
            repository.api
                .getFlowContentDataAsync(CATEGORY_GIRL, CATEGORY_GIRL, page, PAGE_COUNT)
        },
        cacheBlock = {
            //获取缓存数据
            CacheManager.getDefault()
                .getCacheFlow<PageData<Content>>(CONTENT_CACHE_KEY + CATEGORY_GIRL.lowercase())
        },
        completerBlock = {
            //保存首页缓存
            if (contentState.getPage() == contentState.firstPage) {
                LogUtil.i("存储本地数据")
                CacheManager.getDefault().putCache(CONTENT_CACHE_KEY + CATEGORY_GIRL.lowercase(), it)
            }
        },
    )
        .uiState()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(),UiState.Default)

}


class PageStateFlow(val firstPage: Int = 0) {

    private var firstCache = true
    private val pageNumber = PageNumber(firstPage)
    val version = MutableStateFlow(0)

    fun getPage() = pageNumber.page

    fun updateState(@LoadStatus status: Int) {
        LogUtil.i("updateState:$status")
        pageNumber.getPage(status)
        LogUtil.i("update version.value")
        version.value = version.value.plus(1)
    }

    fun <T> pageLaunchFlow(
        networkBlock: (Int) -> Flow<T?> = { flowOf(null) },
        cacheBlock: () -> Flow<T?> = { flowOf(null) },
        completerBlock: (T) -> Unit = { }
    ): Flow<T> {
        return version.flatMapLatest {
            LogUtil.i("version update$version")
            flow {
                //首次加载缓存数据
                if (firstCache) {
                    firstCache = false
                    LogUtil.i("加载本地数据")
                    emitAll(cacheBlock())
                }
                //加载网络数据
                LogUtil.i("加载网络数据${getPage()}")
                emitAll(networkBlock(getPage()))
            }
                .notNull()
                .map {
                    //该回调中saveCache/applyData
                    completerBlock(it!!)
                    it
                }
                .flowOnIO()
        }
    }
}