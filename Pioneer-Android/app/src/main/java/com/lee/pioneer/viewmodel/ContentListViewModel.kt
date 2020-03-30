package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.library.utils.LogUtil
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.Data
import com.lee.pioneer.model.entity.WanData
import com.lee.pioneer.model.repository.ApiRepository

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description
 */
class ContentListViewModel(application: Application) : BaseViewModel(application) {

    var page = 0
    val contentListObservable by lazy { MutableLiveData<Data<Content>>() }
    val bannerObservable by lazy { MutableLiveData<Data<Banner>>() }
    val wandataObservable by lazy { MutableLiveData<WanData>() }

    fun loadListData(type: String, isLoadMore: Boolean) {
        if (!isLoadMore) page = 0
        launch(-1) {
            val response = ApiRepository.getApi().getCategoryDataAsync(
                KeyConstants.CATEGORY_ALL,
                type,
                ++page,
                20
            ).await()
            contentListObservable.value = response
        }
    }

    fun loadBanner() {
        launch(-2) {
            val response = ApiRepository.getApi().getBannerAsync().await()
            bannerObservable.value = response
        }
    }

    fun loadWanData() {
        launch(-3) {
            val response = ApiRepository.getApi()
                .getWanData("https://www.wanandroid.com/article/list/0/json").await()
            wandataObservable.value = response
        }
    }

}