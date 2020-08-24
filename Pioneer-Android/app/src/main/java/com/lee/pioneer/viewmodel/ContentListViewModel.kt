package com.lee.pioneer.viewmodel

import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.mvvm.live.LoadStatus
import com.lee.library.mvvm.live.PageLiveData
import com.lee.pioneer.constants.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.model.entity.*
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository
import com.lee.pioneer.model.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容列表 ViewModel
 */
class ContentListViewModel : BaseViewModel() {

    val contentListData by lazy { PageLiveData<PageData<Content>>(limit = 1) }

    /**
     * 获取contentList列表
     */
    fun loadListData(
        @LoadStatus status: Int,
        type: String
    ) {
        launchMain(-1) {
            contentListData.pageLaunch(status,
                { page: Int ->
                    //网络数据
                    ApiRepository.getApi().getContentDataAsync(
                        KeyConstants.CATEGORY_ALL, type, page, KeyConstants.PAGE_COUNT
                    ).await().also { response ->
                        //填充历史数据 让activity在重建时可以从liveData中获取到完整数据 首页无需填充原始数据(会造成数据重复)
                        contentListData.value?.data?.let { localData ->
                            if (page != contentListData.limit) response.data.addAll(0, localData)
                        }
                    }
                },
                {
                    //缓存数据
                    CacheRepository.get()
                        .getContentCacheAsync(CONTENT_CACHE_KEY + type.toLowerCase(Locale.getDefault()))
                        .await()
                },
                {
                    //存储缓存数据
                    CacheRepository.get()
                        .putCache(CONTENT_CACHE_KEY + type.toLowerCase(Locale.getDefault()), it)
                })
        }
    }

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