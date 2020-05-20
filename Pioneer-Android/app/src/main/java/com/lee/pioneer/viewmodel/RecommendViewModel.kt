package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.vm.ResponsePageViewModel
import com.lee.library.utils.LogUtil
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
class RecommendViewModel(application: Application) : ResponsePageViewModel(application) {

    val bannerObservable by lazy { MutableLiveData<ArrayList<Banner>>() }
    val contentObservable by lazy { MutableLiveData<ArrayList<Content>>() }
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
            ApiRepository.getApi().getBannerAsync()
                .await().data
                .also { bannerObservable.value = it }
        }
//        cacheLaunch(-2,
//            {
//                CacheRepository.get().getBannerCacheAsync(RECOMMEND_BANNER_KEY)
//                    .await()
//                    ?.let { bannerObservable.value = ArrayList(it) }
//            },
//            {
//                ApiRepository.getApi().getBannerAsync()
//                    .await().data
//                    .also { bannerObservable.value = it }
//            },
//            {
//                CacheRepository.get().putCache(RECOMMEND_BANNER_KEY, it.toList())
//            }
//        )
    }

    fun getContentList(type: String) {
        val data = getCacheContentList(type)
        if (data.isNotEmpty()) {
            contentObservable.value = data
            return
        }
        cacheLaunch(-1,
            {
                CacheRepository.get().getContentCacheAsync(
                    RECOMMEND_CACHE_KEY + type.toLowerCase(
                        Locale.getDefault()
                    )
                ).await()
                    ?.let {
                        putCacheContentList(type, it.data)
                        contentObservable.value = it.data
                    }
            },
            {
                ApiRepository.getApi().getHotDataAsync(type, CATEGORY_RECOMMEND, PAGE_COUNT)
                    .await()
                    .also {
                        putCacheContentList(type, it.data)
                        contentObservable.value = it.data
                    }
            },
            {
                CacheRepository.get()
                    .putCache(RECOMMEND_CACHE_KEY + type.toLowerCase(Locale.getDefault()), it)
            })
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