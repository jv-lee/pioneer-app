package com.lee.pioneer.view.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.Cache
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.KeyboardUtil
import com.lee.library.utils.LogUtil
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
        LogUtil.i("time - ${System.currentTimeMillis() - Cache.firstTime}")
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
                executePageCompleted(it, mAdapter, refreshBlock = {
                    mAdapter.addData(it.data)
                    mAdapter.notifyDataSetChanged()
                }, emptyBlock = {
                    toast("未搜索到数据")
                })
            })

            failedEvent.observe(this@SearchFragment, Observer {
                it.message?.let { it1 -> toast(it1) }
            })
        }
    }


}
