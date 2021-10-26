package com.lee.pioneer.girl.viewmodel

import com.lee.library.cache.CacheManager
import com.lee.library.extensions.getCache
import com.lee.library.extensions.putCache
import com.lee.library.mvvm.livedata.LoadStatus
import com.lee.library.mvvm.ui.UiStatePageLiveData
import com.lee.library.mvvm.viewmodel.CoroutineViewModel
import com.lee.pioneer.girl.model.repository.ApiRepository
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_GIRL
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.library.common.entity.*
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlViewModel : CoroutineViewModel() {

    private val cacheManager = CacheManager.getDefault()

    private val meService = ModuleService.find<MeService>()

    private val repository = ApiRepository()

    val contentLive = UiStatePageLiveData(1)

    fun getGirlContentData(@LoadStatus status: Int) {
        launchMain {
            contentLive.pageLaunch(status, { page: Int ->
                repository.api.getContentDataAsync(CATEGORY_GIRL, CATEGORY_GIRL, page, PAGE_COUNT)
                    .also { response ->
                        //填充历史数据 让activity在重建时可以从liveData中获取到完整数据 首页无需填充原始数据(会造成数据重复)
                        contentLive.applyData(
                            contentLive.getValueData<PageData<Content>>()?.data,
                            response.data
                        )
                    }
            }, {
                cacheManager.getCache<PageData<Content>>(CONTENT_CACHE_KEY + CATEGORY_GIRL.lowercase())
            }, {
                cacheManager.putCache(CONTENT_CACHE_KEY + CATEGORY_GIRL.lowercase(), it)
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
                val contentHistory =
                    ContentHistory.parse(ContentType.PICTURE, ContentSource.ID, extends, content)
                meService.insert(contentHistory)
            }
        }
    }

}