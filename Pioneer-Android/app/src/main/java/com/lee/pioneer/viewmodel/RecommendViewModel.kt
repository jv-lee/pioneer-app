package com.lee.pioneer.viewmodel

import com.lee.library.mvvm.live.CacheLiveData
import com.lee.library.mvvm.vm.ResponseViewModel
import com.lee.pioneer.constants.CacheConstants.Companion.RECOMMEND_BANNER_KEY
import com.lee.pioneer.constants.CacheConstants.Companion.RECOMMEND_CACHE_KEY
import com.lee.pioneer.constants.KeyConstants.Companion.CATEGORY_RECOMMEND
import com.lee.pioneer.constants.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.model.entity.*
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository
import com.lee.pioneer.model.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author jv.lee
 * @date 2020/4/9
 * @description
 */
class RecommendViewModel : ResponseViewModel() {

    val bannerData by lazy { CacheLiveData<ArrayList<Banner>>() }
    val contentData by lazy { CacheLiveData<PageData<Content>>() }
    private val viewsData = arrayListOf<Content>()
    private val likesData = arrayListOf<Content>()
    private val commentsData = arrayListOf<Content>()

    /**
     * 浏览记录数据库保存
     */
    fun insertContentHistoryToDB(content: Content) {
        launch {
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

    fun getBannerData() {
        launch(-2) {
            bannerData.cacheLaunch(
                {
                    val response =
                        CacheRepository.get().getBannerCacheAsync(RECOMMEND_BANNER_KEY).await()
                    if (response != null) ArrayList(response) else null
                },
                {
                    ApiRepository.getApi().getBannerAsync()
                        .await().data
                },
                {
                    CacheRepository.get().putCache(RECOMMEND_BANNER_KEY, it.toList())
                })
        }
    }

    fun getContentList(type: String) {
        val data = getCacheContentList(type)
        if (data.isNotEmpty()) {
            contentData.value = PageData(data, page_count = 0, page = 0)
            return
        }
        launch(-1) {
            contentData.cacheLaunch(
                {
                    CacheRepository.get().getContentCacheAsync(
                        RECOMMEND_CACHE_KEY + type.toLowerCase(Locale.getDefault())
                    ).await()
                        ?.also { putCacheContentList(type, ArrayList(it.data)) }
                },
                {
                    ApiRepository.getApi().getHotDataAsync(type, CATEGORY_RECOMMEND, PAGE_COUNT)
                        .await()
                        .also { putCacheContentList(type, it.data) }
                },
                {
                    CacheRepository.get()
                        .putCache(RECOMMEND_CACHE_KEY + type.toLowerCase(Locale.getDefault()), it)
                })
        }
    }

    private fun putCacheContentList(type: String, data: ArrayList<Content>) {
        when (type) {
            "views" -> viewsData.addAll(data)
            "likes" -> likesData.addAll(data)
            "comments" -> commentsData.addAll(data)
        }
    }

    private fun getCacheContentList(type: String): ArrayList<Content> {
        return when (type) {
            "views" -> viewsData
            "likes" -> likesData
            "comments" -> commentsData
            else -> viewsData
        }
    }

}