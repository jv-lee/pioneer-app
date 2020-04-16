package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.library.mvvm.ResponsePageViewModel
import com.lee.pioneer.constants.CacheConstants
import com.lee.pioneer.constants.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.constants.KeyConstants.Companion.CATEGORY_GIRL
import com.lee.pioneer.constants.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.PageData
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository
import executeResponseAny
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.util.*
import kotlin.random.Random

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlViewModel(application: Application) : ResponsePageViewModel(application, 1) {

    val contentObservable by lazy { MutableLiveData<PageData<Content>>() }

    fun getGirlContentData(isMore: Boolean, isReload: Boolean = false) {
        //数据转换 添加viewType
        pageLaunch(isMore, isReload,
            {
                CacheRepository.get()
                    .getContentCacheAsync(CONTENT_CACHE_KEY + CATEGORY_GIRL.toLowerCase(Locale.getDefault()))
                    .await()?.let { it ->
                        it.data.map { it.viewType = Random.nextInt() % 2 }
                        contentObservable.value = it
                    }
            },
            {
                ApiRepository.getApi()
                    .getContentDataAsync(CATEGORY_GIRL, CATEGORY_GIRL, page, PAGE_COUNT)
                    .await().let { it ->
                        if (contentObservable.value != it) {
                            it.data.map { it.viewType = Random.nextInt() % 2 }
                            contentObservable.value = it
                            return@let it
                        }
                        null
                    }
            },
            {
                CacheRepository.get().putCache(
                    CONTENT_CACHE_KEY + CATEGORY_GIRL.toLowerCase(Locale.getDefault()),
                    it
                )
            })
    }

}