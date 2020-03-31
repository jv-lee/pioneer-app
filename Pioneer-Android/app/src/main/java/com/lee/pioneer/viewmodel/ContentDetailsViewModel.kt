package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.pioneer.model.entity.Details
import com.lee.pioneer.model.repository.ApiRepository
import executeResponse

/**
 * @author jv.lee
 * @date 2020/3/31
 * @description
 */
class ContentDetailsViewModel(application: Application) : BaseViewModel(application) {

    val detailsObserver by lazy { MutableLiveData<Details>() }

    fun getDetailsById(id: String) {
        launch(-1) {
            val response = ApiRepository.getApi().getDetailsAsync(id).await()
            executeResponse(response) { detailsObserver.value = it }
        }
    }


}