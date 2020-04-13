package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.repository.ApiRepository
import executeResponseAny

/**
 * @author jv.lee
 * @date 2020/4/9
 * @description
 */
class RecommendViewModel(application: Application) : BaseViewModel(application) {

    val bannerObservable by lazy { MutableLiveData<List<Banner>>() }
    val contentObservable by lazy { MutableLiveData<List<Content>>() }
    private val viewsData = arrayListOf<Content>()
    private val likesData = arrayListOf<Content>()
    private val commentsData = arrayListOf<Content>()

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
        launch(-1) {
            val response = ApiRepository.getApi().getHotDataAsync(type, "GanHuo", 20).await()
            executeResponseAny(response) {
                putCacheContentList(type,it.data)
                contentObservable.value = it.data
            }
        }
    }

    private fun putCacheContentList(type: String, data: List<Content>) {
        when (type) {
            "views" -> viewsData.addAll(data)
            "likes" -> likesData.addAll(data)
            "comments" -> commentsData.addAll(data)
        }
    }

    private fun getCacheContentList(type: String): List<Content> {
        return when (type) {
            "views" -> viewsData
            "likes" -> likesData
            "comments" -> commentsData
            else -> viewsData
        }
    }

}