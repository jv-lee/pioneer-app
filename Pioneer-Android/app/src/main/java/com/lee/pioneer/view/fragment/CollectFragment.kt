package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentCollectBinding
import com.lee.pioneer.view.adapter.ContentChildAdapter
import com.lee.pioneer.viewmodel.CollectViewModel
import executePageCompleted

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description MeFragment ChildPage -> 收藏页面
 */
class CollectFragment :
    BaseNavigationFragment<FragmentCollectBinding, CollectViewModel>(
        R.layout.fragment_collect,
        CollectViewModel::class.java
    ) {

    private val mAdapter by lazy { ContentChildAdapter(context!!, ArrayList()) }

    override fun bindView() {
        binding.rvContainer.layoutManager = LinearLayoutManager(context)
        binding.rvContainer.adapter = mAdapter.proxy

        mAdapter.initStatusView()
        mAdapter.pageLoading()
        mAdapter.setAutoLoadMoreListener {
            viewModel.loadHistory(true)
        }
        mAdapter.setOnItemClickListener { _, entity, _ ->
            findNavController().navigate(
                CollectFragmentDirections.actionCollectToContentDetails(
                    entity.content._id, KeyConstants.CONST_EMPTY
                )
            )
        }
    }

    override fun bindData() {
        viewModel.apply {
            contentData.observe(this@CollectFragment, Observer {
                executePageCompleted(it, mAdapter, 0)
            })
        }

        viewModel.loadHistory(false)
    }

}
