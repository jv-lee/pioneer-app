package com.lee.pioneer.me.viewmodel

import com.lee.library.mvvm.base.CoroutineViewModel
import com.lee.library.mvvm.livedata.PageLiveData
import com.lee.library.mvvm.load.LoadStatus
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.entity.ContentHistory
import com.lee.pioneer.library.common.entity.PageData
import com.lee.pioneer.library.common.tools.CommonTools
import com.lee.pioneer.me.repository.DataBaseRepository

/**
 * @author jv.lee
 * @date 2020/4/23
 * @description
 */
class HistoryViewModel : CoroutineViewModel() {

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
    fun loadHistory(@LoadStatus status: Int) {
        launchMain {
            contentData.pageLaunch(status,
                { page: Int ->
                    //获取总页数 使用懒加载
                    val pageCount = launchIO {
                        pageCount
                    }
                    //获取当前页
                    val response = launchIO {
                        DataBaseRepository.get().historyDao.queryContentHistory(page)
                    }
                    //通知数据更新
                    PageData(ArrayList(response), page = page, page_count = pageCount)
                })
        }
    }

}