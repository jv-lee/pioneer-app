package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.vm.ResponsePageViewModel
import com.lee.pioneer.constants.CacheConstants.Companion.CONTENT_CACHE_KEY
import com.lee.pioneer.constants.KeyConstants.Companion.CATEGORY_GIRL
import com.lee.pioneer.constants.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.model.entity.*
import com.lee.pioneer.model.repository.ApiRepository
import com.lee.pioneer.model.repository.CacheRepository
import com.lee.pioneer.model.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

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
            val extends = withContext(Dispatchers.IO) {
                DataBaseRepository.get().historyDao.isCollect(content._id)
            }
            withContext(Dispatchers.IO) {
                DataBaseRepository.get().historyDao
                    .insert(
                        ContentHistory.parse(
                            ContentType.PICTURE,
                            ContentSource.ID,
                            extends,
                            content
                        )
                    )
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
                        contentObservable.value = it
                    }
            },
            {
                ApiRepository.getApi()
                    .getContentDataAsync(CATEGORY_GIRL, CATEGORY_GIRL, page, PAGE_COUNT)
                    .await().also { it ->
                        //填充历史数据 让activity在重建时可以从liveData中获取到完整数据
                        contentObservable.value?.data?.let { data ->
                            it.data.addAll(0, data)
                        }
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