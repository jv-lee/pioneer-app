package com.lee.pioneer.viewmodel

import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.mvvm.load.LoadStatus
import com.lee.library.mvvm.live.PageLiveData
import com.lee.library.mvvm.live.applyData
import com.lee.pioneer.constants.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.constants.KeyConstants.Companion.CATEGORY_GIRL
import com.lee.pioneer.constants.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.model.entity.*
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository
import com.lee.pioneer.model.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlViewModel : BaseViewModel() {

    val contentData by lazy { PageLiveData<PageData<Content>>(limit = 1) }

    /**
     * 浏览后添加至数据库
     */
    fun insertContentHistoryToDB(content: Content) {
        launchMain {
            val extends = withContext(Dispatchers.IO) {
                DataBaseRepository.get().historyDao.isCollect(content._id)
            }
            withContext(Dispatchers.IO) {
                DataBaseRepository.get().historyDao
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
        contentData.pageLaunch(
            status,
            { page: Int ->
                ApiRepository.getApi()
                    .getContentDataAsync(CATEGORY_GIRL, CATEGORY_GIRL, page, PAGE_COUNT)
                    .also {
                        //填充历史数据 让activity在重建时可以从liveData中获取到完整数据 首页无需填充原始数据(会造成数据重复)
                        contentData.applyData(page, contentData.limit, contentData.value?.data, it.data)
                    }
            },
            {
                CacheRepository.get()
                    .getContentCacheAsync(CONTENT_CACHE_KEY + CATEGORY_GIRL.toLowerCase(Locale.getDefault()))
                    .await()
            },
            {
                CacheRepository.get().putCache(
                    CONTENT_CACHE_KEY + CATEGORY_GIRL.toLowerCase(Locale.getDefault()), it
                )
            })
    }

}