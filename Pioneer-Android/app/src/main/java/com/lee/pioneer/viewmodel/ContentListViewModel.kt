package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.ResponsePageViewModel
import com.lee.library.utils.LogUtil
import com.lee.pioneer.constants.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.db.AppDataBase
import com.lee.pioneer.model.entity.*
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容列表 ViewModel
 */
class ContentListViewModel(application: Application) : ResponsePageViewModel(application, 1) {

    val contentListObservable by lazy { MutableLiveData<PageData<Content>>() }

    /**
     * 获取Content数据列表
     */
    fun loadListData(type: String, isLoadMore: Boolean, isReload: Boolean = false) {
        pageLaunch(isLoadMore, isReload,
            {
                //缓存数据
                CacheRepository.get().getContentCacheAsync(
                    CONTENT_CACHE_KEY + type.toLowerCase(Locale.getDefault())
                ).await()?.let { it -> contentListObservable.value = it }
            },
            {
                //网络数据
                ApiRepository.getApi().getContentDataAsync(
                    KeyConstants.CATEGORY_ALL, type, page, KeyConstants.PAGE_COUNT
                ).await().let {
                    if (contentListObservable.value != it) {
                        contentListObservable.value = it
                        return@let it
                    }
                    null
                }
            },
            {
                //存储缓存数据
                CacheRepository.get()
                    .putCache(CONTENT_CACHE_KEY + type.toLowerCase(Locale.getDefault()), it)
            })
    }


    /**
     * 浏览后添加至数据库
     */
    fun insertContentHistoryToDB(content: Content) {
        launch {
            withContext(Dispatchers.IO) {
                AppDataBase.get().contentHistoryDao()
                    .insert(ContentHistory.push(HistoryType.CONTENT, HistorySource.ID, content))
            }
        }
    }

}