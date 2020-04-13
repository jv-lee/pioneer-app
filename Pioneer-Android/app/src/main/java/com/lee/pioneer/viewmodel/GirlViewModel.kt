package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.Data
import com.lee.pioneer.model.repository.ApiRepository
import executeResponseAny

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlViewModel(application: Application) : BaseViewModel(application) {

    var page = 0
    val contentObservable by lazy { MutableLiveData<Data<List<Content>>>() }

    fun getGirlContentData(isMore: Boolean) {
        if (!isMore) page = 0
        launch(-1) {
            val response =
                ApiRepository.getApi().getCategoryDataAsync("Girl", "Girl", ++page, 20).await()
            executeResponseAny(response, {
                contentObservable.value = it
            })
        }
    }

}