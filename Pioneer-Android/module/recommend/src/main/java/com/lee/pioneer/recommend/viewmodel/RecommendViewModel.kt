package com.lee.pioneer.recommend.viewmodel

import com.lee.library.cache.CacheManager
import com.lee.library.extensions.getCache
import com.lee.library.extensions.putCache
import com.lee.library.mvvm.live.CacheLiveData
import com.lee.library.mvvm.vm.ResponseViewModel
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.RECOMMEND_BANNER_KEY
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.RECOMMEND_CACHE_KEY
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_RECOMMEND
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.library.common.entity.*
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService
import com.lee.pioneer.recommend.model.repository.ApiRepository

/**
 * @author jv.lee
 * @date 2020/4/9
 * @description
 */
class RecommendViewModel : ResponseViewModel() {

    private val meService by lazy { ModuleService.find<MeService>() }

    private val repository by lazy { ApiRepository() }

    val bannerData by lazy { CacheLiveData<ArrayList<Banner>>() }
    val contentData by lazy { CacheLiveData<PageData<Content>>() }
    private val viewsData = arrayListOf<Content>()
    private val likesData = arrayListOf<Content>()
    private val commentsData = arrayListOf<Content>()

    /**
     * 浏览记录数据库保存
     */
    fun insertContentHistoryToDB(content: Content) {
        launchMain {
            launchIO {
                val extends = meService.isCollect(content._id)
                meService
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
        launchMain {
            bannerData.cacheLaunch(
                {
                    val response =
                        CacheManager.getDefault().getCache<List<Banner>>(RECOMMEND_BANNER_KEY)
                    if (response != null) ArrayList(response) else null
                },
                {
                    repository.api.getBannerAsync().data
                },
                {
                    CacheManager.getDefault().putCache(RECOMMEND_BANNER_KEY, it.toList())
                })
        }
    }

    fun getContentList(type: String) {
        val data = getCacheContentList(type)
        if (data.isNotEmpty()) {
            contentData.value = PageData(data, page_count = 0, page = 0)
            return
        }
        launchMain {
            contentData.cacheLaunch(
                {
                    CacheManager.getDefault()
                        .getCache<PageData<Content>>(RECOMMEND_CACHE_KEY + type.lowercase())
                        ?.also { putCacheContentList(type, ArrayList(it.data)) }
                },
                {
                    repository.api.getHotDataAsync(type, CATEGORY_RECOMMEND, PAGE_COUNT)
                        .also { putCacheContentList(type, it.data) }
                },
                {
                    CacheManager.getDefault()
                        .putCache(RECOMMEND_CACHE_KEY + type.lowercase(), it)
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