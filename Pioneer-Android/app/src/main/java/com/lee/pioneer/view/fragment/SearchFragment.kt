package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.KeyboardUtil
import com.lee.library.widget.StatusLayout
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentSearchBinding
import com.lee.pioneer.view.adapter.ContentAdapter
import com.lee.pioneer.viewmodel.SearchViewModel
import executePageCompleted

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 搜索页
 */
class SearchFragment :
    BaseNavigationFragment<FragmentSearchBinding, SearchViewModel>(
        R.layout.fragment_search,
        SearchViewModel::class.java
    ) {

    private val mAdapter by lazy { ContentAdapter(context!!, ArrayList()) }

    override fun bindView() {
        binding.tvCancel.setOnClickListener {
            KeyboardUtil.hideSoftInput(activity)
            findNavController().popBackStack()
        }

        binding.rvContainer.layoutManager = LinearLayoutManager(context)
        binding.rvContainer.adapter = mAdapter.proxy

        mAdapter.openLoadMore()
        mAdapter.setOnItemClickListener { _, entity, _ ->
            hideNavigation()
            findNavController().navigate(
                SearchFragmentDirections.actionSearchToContentDetails(entity._id)
            )
        }
        mAdapter.setAutoLoadMoreListener {
            viewModel.searchDataList(true)
        }
    }

    override fun bindData() {
        binding.vm = viewModel

        viewModel.apply {
            contentListObservable.observe(this@SearchFragment, Observer {
                executePageCompleted(it, mAdapter,
                    refreshBlock = {
                        binding.status.setStatus(StatusLayout.STATUS_DATA)
                    }, emptyBlock = {
                        binding.status.setStatus(StatusLayout.STATUS_EMPTY_DATA)
                    })
            })

            failedEvent.observe(this@SearchFragment, Observer {
                it.message?.let { it1 -> toast(it1) }
            })

            loadingObservable.observe(this@SearchFragment, Observer {
                binding.status.setStatus(StatusLayout.STATUS_LOADING)
            })
        }
    }


}
