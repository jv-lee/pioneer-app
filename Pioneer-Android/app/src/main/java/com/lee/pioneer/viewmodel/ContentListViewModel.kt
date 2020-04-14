package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.library.utils.LogUtil
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.PageData
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository
import executeResponseAny

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容列表 ViewModel
 */
class ContentListViewModel(application: Application) : BaseViewModel(application) {

    var page = 0
    val contentListObservable by lazy { MutableLiveData<PageData<Content>>() }

    fun loadListData(type: String, isLoadMore: Boolean) {
        if (!isLoadMore) page = 0
        launch(-1) {
//            val dataCache =
//                CacheRepository.get().getDataCache<PageData<Content>>("content-key2")
//            dataCache?.let {
//                LogUtil.i("dataCache:" + dataCache)
//            }
            val response = ApiRepository.getApi().getCategoryDataAsync(
                KeyConstants.CATEGORY_ALL,
                type,
                ++page,
                20
            ).await()
            executeResponseAny(response) {
                CacheRepository.get().putDataCache("content-key2", it)
                contentListObservable.value = it
            }
        }
    }

}