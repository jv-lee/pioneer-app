package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.ResponsePageViewModel
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
class HomeViewModel(application: Application) : ResponsePageViewModel(application) {

    val categoryObservable by lazy { MutableLiveData<PageData<Category>>() }

    /**
     * TODO 构建主页分类tab 子fragments  启动缓存及网络数据加载
     */
    fun buildCategoryFragment() {
        cacheLaunch(
            {
                //加载本地缓存
                CacheRepository.get().getCategoryCacheAsync(CATEGORY_CACHE_KEY).await()
                    ?.let { categoryObservable.value = it }
            },
            {
                //加载网络数据 与本地数据对比 相等则返回空不继续执行下一个方法体
                ApiRepository.getApi().getCategoriesAsync(CATEGORY_TYPE).await()
                    .let { if (categoryObservable.value == it) null else it }
            },
            {
                //网络数据与本地数据不一致 存储缓存 设置网络数据
                categoryObservable.value = it
                CacheRepository.get().putCache(CATEGORY_CACHE_KEY, it)
            })
    }

}