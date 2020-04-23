package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.LogUtil
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentHistoryBinding
import com.lee.pioneer.view.adapter.ContentChildAdapter
import com.lee.pioneer.viewmodel.HistoryViewModel

/**
 * @author jv.lee
 * @date 2020/4/17
 * @description MeFragment ChildPage -> 浏览记录页面
 */
class HistoryFragment :
    BaseNavigationFragment<FragmentHistoryBinding, HistoryViewModel>(
        R.layout.fragment_history,
        HistoryViewModel::class.java
    ) {

    val mAdapter by lazy { ContentChildAdapter(context!!, ArrayList()) }

    override fun bindView() {

    }

    override fun bindData() {
        viewModel.apply {
            dataObservable.observe(this@HistoryFragment, Observer {
                LogUtil.i("$it")
            })
        }

        viewModel.loadHistory(false)
    }

}
