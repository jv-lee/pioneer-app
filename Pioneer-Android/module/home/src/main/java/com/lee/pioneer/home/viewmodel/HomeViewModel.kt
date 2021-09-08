package com.lee.pioneer.home.viewmodel

import com.lee.library.mvvm.live.CacheLiveData
import com.lee.library.mvvm.live.RestorePageLiveData
import com.lee.library.mvvm.vm.ResponseViewModel
import com.lee.pioneer.constants.CacheConstants.Companion.CATEGORY_CACHE_KEY
import com.lee.pioneer.constants.KeyConstants.Companion.CATEGORY_TYPE
import com.lee.pioneer.model.entity.Category
import com.lee.pioneer.model.entity.PageData
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository

/**
 * @author jv.lee
 * @date 2020/3/26
 * @description 主页ViewModel
 */
class HomeViewModel : ResponseViewModel() {

    val categoryData by lazy { CacheLiveData<PageData<Category>>() }
    val restoreHomePageLiveData by lazy { RestorePageLiveData() }

    /**
     *  构建主页分类tab 子fragments  启动缓存及网络数据加载
     */
    fun buildCategoryFragment() {
        launchMain {
            categoryData.cacheLaunch(
                {
                    CacheRepository.get().getCategoryCacheAsync(CATEGORY_CACHE_KEY).await()
                },
                {
                    ApiRepository.getApi().getCategoriesAsync(CATEGORY_TYPE)
                },
                {
                    CacheRepository.get().putCache(CATEGORY_CACHE_KEY, it)
                })
        }
    }

}