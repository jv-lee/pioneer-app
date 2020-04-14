package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.library.utils.LogUtil
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.Data
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
    val contentListObservable by lazy { MutableLiveData<Data<List<Content>>>() }

    fun loadListData(type: String, isLoadMore: Boolean) {
        if (!isLoadMore) page = 0
        launch(-1) {
            val dataCache = CacheRepository.get().getDataCache<Data<List<Content>>>("content-key")
            dataCache?.let {
                LogUtil.i("dataCache:"+dataCache.data)
            }
            val response = ApiRepository.getApi().getCategoryDataAsync(
                KeyConstants.CATEGORY_ALL,
                type,
                ++page,
                20
            ).await()
            executeResponseAny(response) {
                CacheRepository.get().putDataCache("content-key",it)
                contentListObservable.value = it
            }
        }
    }

}