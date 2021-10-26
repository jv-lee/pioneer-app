package com.lee.pioneer.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.lee.library.cache.CacheManager
import com.lee.library.extensions.getCache
import com.lee.library.extensions.putCache
import com.lee.library.mvvm.ui.UiState
import com.lee.library.mvvm.livedata.LoadStatusLiveData
import com.lee.library.mvvm.ui.stateCacheFlow
import com.lee.library.mvvm.ui.stateCacheLive
import com.lee.library.mvvm.viewmodel.CoroutineViewModel
import com.lee.pioneer.home.model.repository.ApiRepository
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.CATEGORY_CACHE_KEY
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_TYPE
import com.lee.pioneer.library.common.entity.Category
import com.lee.pioneer.library.common.entity.PageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

/**
 * @author jv.lee
 * @date 2020/3/26
 * @description 主页ViewModel
 */
class HomeViewModel : CoroutineViewModel() {

    private val repository = ApiRepository()

    private val _categoryLive = MutableLiveData<UiState>()
    val categoryLive: LiveData<UiState> = _categoryLive

    fun requestCategory() {
        launchMain {
            stateCacheFlow({
                repository.api.getCategoriesAsync(CATEGORY_TYPE)
            }, {
                CacheManager.getDefault().getCache<PageData<Category>>(CATEGORY_CACHE_KEY)
            }, {
                CacheManager.getDefault().putCache(CATEGORY_CACHE_KEY, it)
            })
                .flowOn(Dispatchers.IO)
                .collectLatest {
                    _categoryLive.postValue(it)
                }
        }
    }

    init {
        requestCategory()
    }

}