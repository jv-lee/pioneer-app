package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.ResponsePageViewModel
import com.lee.pioneer.constants.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.constants.KeyConstants.Companion.CATEGORY_GIRL
import com.lee.pioneer.constants.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.db.AppDataBase
import com.lee.pioneer.model.entity.*
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.random.Random

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlViewModel(application: Application) : ResponsePageViewModel(application, 1) {

    val contentObservable by lazy { MutableLiveData<PageData<Content>>() }

    /**
     * 浏览后添加至数据库
     */
    fun insertContentHistoryToDB(content: Content) {
        launch {
            withContext(Dispatchers.IO) {
                AppDataBase.get().contentHistoryDao()
                    .insert(ContentHistory.push(HistoryType.PICTURE, HistorySource.ID, content))
            }
        }
    }

    fun getGirlContentData(isMore: Boolean, isReload: Boolean = false) {
        //数据转换 添加viewType
        pageLaunch(isMore, isReload,
            {
                CacheRepository.get()
                    .getContentCacheAsync(CONTENT_CACHE_KEY + CATEGORY_GIRL.toLowerCase(Locale.getDefault()))
                    .await()?.let { it ->
//                        it.data.map { it.viewType = Random.nextInt() % 2 }
                        contentObservable.value = it
                    }
            },
            {
                ApiRepository.getApi()
                    .getContentDataAsync(CATEGORY_GIRL, CATEGORY_GIRL, page, PAGE_COUNT)
                    .await().also { it ->
//                        it.data.map { it.viewType = Random.nextInt() % 2 }
                        contentObservable.value = it
                    }
            },
            {
                CacheRepository.get().putCache(
                    CONTENT_CACHE_KEY + CATEGORY_GIRL.toLowerCase(Locale.getDefault()), it
                )
            })
    }

}