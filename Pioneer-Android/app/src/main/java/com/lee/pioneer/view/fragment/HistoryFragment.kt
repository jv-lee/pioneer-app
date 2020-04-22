package com.lee.pioneer.view.fragment

import androidx.lifecycle.ViewModel
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.LogUtil
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentHistoryBinding
import com.lee.pioneer.model.repository.DataBaseRepository
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020/4/17
 * @description MeFragment ChildPage -> 浏览记录页面
 */
class HistoryFragment :
    BaseNavigationFragment<FragmentHistoryBinding, ViewModel>(R.layout.fragment_history, null) {

    override fun bindView() {

    }

    override fun bindData() {
        launch {
            val response = DataBaseRepository.get().historyDao.queryContentHistory()
            response.forEach {
                LogUtil.i("roomData->${it.content.title} ${it.isCollect}")
            }
        }
    }

}
