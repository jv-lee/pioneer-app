package com.lee.pioneer.home.viewmodel

import com.lee.library.cache.CacheManager
import com.lee.library.extensions.getCache
import com.lee.library.extensions.putCache
import com.lee.library.mvvm.base.CoroutineViewModel
import com.lee.library.mvvm.livedata.PageLiveData
import com.lee.library.mvvm.livedata.applyData
import com.lee.library.mvvm.livedata.pageLaunch
import com.lee.library.mvvm.load.LoadStatus
import com.lee.pioneer.home.model.repository.ApiRepository
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.entity.*
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容列表 ViewModel
 */
class ContentListViewModel : CoroutineViewModel() {

    private val meService by lazy { ModuleService.find<MeService>() }

    private val repository by lazy { ApiRepository() }

    val contentListData by lazy { PageLiveData<PageData<Content>>(initPage = 1) }

    /**
     * 获取contentList列表
     */
    fun loadListData(
        @LoadStatus status: Int,
        type: String
    ) {
        launchMain {
            contentListData.pageLaunch(status,
                { page: Int ->
                    //网络数据
                    repository.api.getContentDataAsync(
                        KeyConstants.CATEGORY_ALL, type, page, KeyConstants.PAGE_COUNT
                    ).also { response ->
                        //填充历史数据 让activity在重建时可以从liveData中获取到完整数据 首页无需填充原始数据(会造成数据重复)
                        contentListData.applyData(contentListData.value?.data, response.data)
                    }
                },
                {
                    //缓存数据
                    CacheManager.getDefault()
                        .getCache<PageData<Content>>(CONTENT_CACHE_KEY + type.lowercase())
                },
                {
                    //存储缓存数据
                    CacheManager.getDefault()
                        .putCache(CONTENT_CACHE_KEY + type.lowercase(), it)
                })
        }
    }

    /**
     * 浏览后添加至数据库
     */
    fun insertContentHistoryToDB(content: Content) {
        launchMain {
            launchIO {
                val extends = meService.isCollect(content._id)
                meService.insert(
                    ContentHistory.parse(
                        ContentType.CONTENT,
                        ContentSource.ID,
                        extends,
                        content
                    )
                )
            }
        }
    }

}