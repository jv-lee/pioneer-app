package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.vm.ResponsePageViewModel
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.model.entity.ContentHistory
import com.lee.pioneer.model.entity.PageData
import com.lee.pioneer.model.repository.DataBaseRepository
import com.lee.pioneer.tools.CommonTools
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jv.lee
 * @date 2020/4/23
 * @description
 */
class HistoryViewModel(application: Application) : ResponsePageViewModel(application) {

    val dataObservable by lazy { MutableLiveData<PageData<ContentHistory>>() }
    private val pageCount by lazy {
        CommonTools.totalToPage(
            DataBaseRepository.get().historyDao.queryContentHistoryCount(),
            KeyConstants.PAGE_COUNT
        )
    }

    /**
     * 加载本地数据库历史记录
     */
    fun loadHistory(isLoadMore: Boolean) {
        pageLaunch(isLoadMore, resumeBlock = {
            //获取总页数 使用懒加载
            val pageCount = withContext(Dispatchers.IO) {
                pageCount
            }
            //获取当前页
            val response = withContext(Dispatchers.IO) {
                DataBaseRepository.get().historyDao.queryContentHistory(page)
            }
            //通知数据更新
            dataObservable.value =
                PageData(ArrayList(response), page = page, page_count = pageCount)
        })
    }

}