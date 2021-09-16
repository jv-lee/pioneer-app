package com.lee.pioneer.home.viewmodel

import com.lee.library.cache.CacheManager
import com.lee.library.extensions.getCache
import com.lee.library.extensions.putCache
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.mvvm.livedata.CacheLiveData
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
class HomeViewModel : BaseViewModel() {

    private val repository by lazy { ApiRepository() }

    val categoryData by lazy { CacheLiveData<PageData<Category>>() }

    init {
        buildCategoryFragment()
    }

    /**
     *  构建主页分类tab 子fragments  启动缓存及网络数据加载
     */
    fun buildCategoryFragment() {
        launchMain {
            categoryData.cacheLaunch(
                {
                    CacheManager.getDefault().getCache<PageData<Category>>(CATEGORY_CACHE_KEY)
                },
                {
                    repository.api.getCategoriesAsync(CATEGORY_TYPE)
                },
                {
                    CacheManager.getDefault().putCache(CATEGORY_CACHE_KEY, it)
                })
        }
    }

}