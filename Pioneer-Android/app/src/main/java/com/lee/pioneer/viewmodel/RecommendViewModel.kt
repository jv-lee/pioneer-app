package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.base.BaseApplication
import com.lee.library.mvvm.BaseViewModel
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.model.repository.ApiRepository
import executeResponseAny

/**
 * @author jv.lee
 * @date 2020/4/9
 * @description
 */
class RecommendViewModel(application: Application) : BaseViewModel(application) {

    val bannerObservable by lazy { MutableLiveData<List<Banner>>() }

    fun getBannerData() {
        launch(-2) {
            val response = ApiRepository.getApi().getBannerAsync().await()
            executeResponseAny(response, {
                bannerObservable.value = it.data
            })
        }
    }

}