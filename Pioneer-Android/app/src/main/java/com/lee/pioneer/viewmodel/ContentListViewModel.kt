package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.library.utils.LogUtil
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.Data
import com.lee.pioneer.model.repository.ApiRepository

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description
 */
class ContentListViewModel(application: Application) : BaseViewModel(application) {

    var page = 1
    val contentListObservable by lazy { MutableLiveData<Data<Content>>() }

    fun loadListData(type: String, isLoadMore: Boolean) {
        if (!isLoadMore) page = 1
        launch(-1) {
            LogUtil.i("loadData Start")
            val response = ApiRepository.getApi().getCategoryDataAsync(
                KeyConstants.CATEGORY_ALL,
                type,
                page,
                20
            ).await()
            LogUtil.i("loadData End")
            contentListObservable.value = response
        }
    }

}