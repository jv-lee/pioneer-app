package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.KeyboardUtil
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentSearchBinding
import com.lee.pioneer.view.adapter.ContentAdapter
import com.lee.pioneer.viewmodel.SearchViewModel
import executePageCompleted
import executePageError

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

        mAdapter.initStatusView()
        mAdapter.setOnItemClickListener { _, entity, _ ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchToContentDetails(
                    entity._id,
                    KeyConstants.CONST_EMPTY
                )
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
                executePageCompleted(it, mAdapter)
            })

            //错误处理
            failedEvent.observe(this@SearchFragment, Observer { it ->
                it?.message?.let { toast(it) }
                when (it.code) {
                    -1 -> executePageError(mAdapter, null)
                }
            })

            loadingObservable.observe(this@SearchFragment, Observer {
                mAdapter.initStatusView()
                mAdapter.pageLoading()
            })
        }
    }


}
