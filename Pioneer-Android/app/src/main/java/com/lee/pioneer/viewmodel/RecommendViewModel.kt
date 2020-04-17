package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.ResponsePageViewModel
import com.lee.pioneer.constants.CacheConstants.Companion.RECOMMEND_CACHE_KEY
import com.lee.pioneer.constants.KeyConstants.Companion.CATEGORY_RECOMMEND
import com.lee.pioneer.constants.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.db.AppDataBase
import com.lee.pioneer.model.entity.*
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository
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
     * 浏览后添加至数据库
     */
    fun insertContentHistoryToDB(content: Content) {
        launch {
            withContext(Dispatchers.IO) {
                AppDataBase.get().contentHistoryDao()
                    .insert(ContentHistory.push(HistoryType.CONTENT, HistorySource.ID, content))
            }
        }
    }

    fun getBannerData() {
        launch(-2) {
            val response = ApiRepository.getApi().getBannerAsync().await()
            executeResponseAny(response, {
                bannerObservable.value = it.data
            })
        }
    }

    fun getContentList(type: String) {
        val data = getCacheContentList(type)
        if (data.isNotEmpty()) {
            contentObservable.value = data
            return
        }
        cacheLaunch(
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
                    .let {
                        if (contentObservable.value != it.data) {
                            putCacheContentList(type, it.data)
                            contentObservable.value = it.data
                            return@let it
                        }
                        null
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