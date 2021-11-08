package com.lee.pioneer.me.viewmodel

import com.lee.library.mvvm.livedata.LoadStatus
import com.lee.library.mvvm.ui.UiStatePageLiveData
import com.lee.library.mvvm.viewmodel.CoroutineViewModel
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.entity.PageData
import com.lee.pioneer.library.common.tools.CommonTools
import com.lee.pioneer.me.repository.DataBaseRepository

/**
 * @author jv.lee
 * @date 2020/4/23
 * @description
 */
class HistoryViewModel : CoroutineViewModel() {

    val contentLive = UiStatePageLiveData(0)

    /**
     * 加载本地数据库历史记录
     */
    fun loadHistory(@LoadStatus status: Int) {
        launchIO {
            contentLive.pageLaunch(status,
                { page: Int ->
                    //获取总页数 使用懒加载
                    val count = DataBaseRepository.get().historyDao.queryContentHistoryCount()
                    val pageCount = CommonTools.totalToPage(count, KeyConstants.PAGE_COUNT)

                    //获取当前页
                    val response = DataBaseRepository.get().historyDao.queryContentHistory(page)
                    //通知数据更新
                    PageData(ArrayList(response), page = page, page_count = pageCount)
                })
        }
    }

}