package com.lee.pioneer.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.lee.library.cache.CacheManager
import com.lee.library.extensions.getCache
import com.lee.library.extensions.putCache
import com.lee.library.mvvm.ui.UiState
import com.lee.library.mvvm.livedata.LoadStatusLiveData
import com.lee.library.mvvm.ui.stateCacheLive
import com.lee.library.mvvm.viewmodel.CoroutineViewModel
import com.lee.pioneer.home.model.repository.ApiRepository
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.CATEGORY_CACHE_KEY
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_TYPE
import com.lee.pioneer.library.common.entity.Category
import com.lee.pioneer.library.common.entity.PageData

/**
 * @author jv.lee
 * @date 2020/3/26
 * @description 主页ViewModel
 */
class HomeViewModel : CoroutineViewModel() {

    private val repository = ApiRepository()

    private val categoryUiState = LoadStatusLiveData()

    val categoryLive: LiveData<UiState> = categoryUiState.switchMap {
        stateCacheLive({
            CacheManager.getDefault().getCache<PageData<Category>>(CATEGORY_CACHE_KEY)
        }, {
            repository.api.getCategoriesAsync(CATEGORY_TYPE)
        }, {
            CacheManager.getDefault().putCache(CATEGORY_CACHE_KEY, it)
        })
    }

    fun categoryReload() {
        categoryUiState.reload()
    }

}