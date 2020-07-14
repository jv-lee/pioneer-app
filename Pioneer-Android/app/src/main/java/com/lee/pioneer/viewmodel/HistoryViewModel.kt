package com.lee.pioneer.viewmodel

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
 * @date 2020/4/23
 * @description
 */
class HistoryViewModel : ResponseViewModel() {

    val contentData by lazy { PageLiveData<PageData<ContentHistory>>(limit = 0) }
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
        launch {
            contentData.pageLaunch(isLoadMore = isLoadMore, resumeBlock = { page: Int ->
                //获取总页数 使用懒加载
                val pageCount = withContext(Dispatchers.IO) {
                    pageCount
                }
                //获取当前页
                val response = withContext(Dispatchers.IO) {
                    DataBaseRepository.get().historyDao.queryContentHistory(page)
                }
                //通知数据更新
                PageData(ArrayList(response), page = page, page_count = pageCount)
            })
        }
    }

}