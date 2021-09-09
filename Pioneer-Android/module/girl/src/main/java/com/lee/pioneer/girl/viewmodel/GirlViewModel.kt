package com.lee.pioneer.girl.viewmodel

import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.mvvm.live.PageLiveData
import com.lee.library.mvvm.live.applyData
import com.lee.library.mvvm.load.LoadStatus
import com.lee.pioneer.girl.model.repository.ApiRepository
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_GIRL
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.library.common.model.entity.*
import com.lee.pioneer.library.common.model.repository.CacheRepository
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService

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
            contentData.pageLaunch(
                status,
                { page: Int ->
                    repository.api
                        .getContentDataAsync(CATEGORY_GIRL, CATEGORY_GIRL, page, PAGE_COUNT)
                        .also {
                            //填充历史数据 让activity在重建时可以从liveData中获取到完整数据 首页无需填充原始数据(会造成数据重复)
                            contentData.applyData(contentData.value?.data, it.data)
                        }
                },
                {
                    CacheRepository.get()
                        .getCache<PageData<Content>>(CONTENT_CACHE_KEY + CATEGORY_GIRL.lowercase())
                },
                {
                    CacheRepository.get().putCache(
                        CONTENT_CACHE_KEY + CATEGORY_GIRL.lowercase(), it
                    )
                })
        }
    }

}