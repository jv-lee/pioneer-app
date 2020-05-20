package com.lee.pioneer.viewmodel

import android.app.Application
import com.lee.library.mvvm.live.PageLiveData
import com.lee.library.mvvm.vm.ResponseViewModel
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.model.entity.ContentHistory
import com.lee.pioneer.model.entity.PageData
import com.lee.pioneer.model.repository.DataBaseRepository
import com.lee.pioneer.tools.CommonTools
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jv.lee
 * @date 2020/4/22
 * @description
 */
class CollectViewModel(application: Application) : ResponseViewModel(application) {

    val contentData by lazy { PageLiveData<PageData<ContentHistory>>(limit = 0) }
    private val pageCount by lazy {
        CommonTools.totalToPage(
            DataBaseRepository.get().historyDao.queryContentCollectCount(),
            KeyConstants.PAGE_COUNT
        )
    }

    /**
     * 加载本地数据库历史记录
     */
    fun loadHistory(isLoadMore: Boolean) {
        contentData.pageLaunch(isLoadMore, resumeBlock = { page: Int, limit: Int ->
            //获取总页数 使用懒加载
            val pageCount = withContext(Dispatchers.IO) { pageCount }
            //获取当前页
            val response = withContext(Dispatchers.IO) {
                DataBaseRepository.get().historyDao.queryContentCollect(page)
            }
            PageData(ArrayList(response), page = page, page_count = pageCount)
        })
    }
}