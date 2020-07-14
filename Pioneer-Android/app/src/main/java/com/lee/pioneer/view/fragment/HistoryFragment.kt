package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentHistoryBinding
import com.lee.pioneer.view.adapter.ContentChildAdapter
import com.lee.pioneer.viewmodel.HistoryViewModel
import executePageCompleted

/**
 * @author jv.lee
 * @date 2020/4/17
 * @description MeFragment ChildPage -> 浏览记录页面
 */
class HistoryFragment :
    BaseNavigationFragment<FragmentHistoryBinding, HistoryViewModel>(R.layout.fragment_history) {

    private val mAdapter by lazy { ContentChildAdapter(context!!, ArrayList()) }

    override fun bindView() {
        binding.rvContainer.layoutManager = LinearLayoutManager(context)
        binding.rvContainer.adapter = mAdapter.proxy

        mAdapter.initStatusView()
        mAdapter.pageLoading()
        mAdapter.setAutoLoadMoreListener {
            viewModel.loadHistory(true)
        }
        mAdapter.setOnItemClickListener { view, entity, position ->
            findNavController().navigate(
                HistoryFragmentDirections.actionHistoryToContentDetails(
                    entity.content._id, KeyConstants.CONST_EMPTY
                )
            )
        }
    }

    override fun bindData() {
        viewModel.apply {
            contentData.observe(this@HistoryFragment, Observer {
                executePageCompleted(it, mAdapter, 0)
            })
        }

        viewModel.loadHistory(false)
    }

}
