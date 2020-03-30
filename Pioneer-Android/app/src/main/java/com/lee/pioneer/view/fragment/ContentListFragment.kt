package com.lee.pioneer.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.widget.StatusLayout.*
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentContentListBinding
import com.lee.pioneer.view.adapter.ContentAdapter
import com.lee.pioneer.viewmodel.ContentListViewModel
import executePageCompleted

private const val ARG_PARAM_TYPE = "arg_param_type"

/**
 * @author jv.lee
 * TODO 内容列表页面
 */
class ContentListFragment :
    BaseNavigationFragment<FragmentContentListBinding, ContentListViewModel>(
        R.layout.fragment_content_list,
        ContentListViewModel::class.java
    ) {
    private var type: String? = null
    private val mAdapter by lazy { ContentAdapter(context!!, ArrayList()) }

    companion object {
        @JvmStatic
        fun newInstance(type: String) = ContentListFragment().apply {
            arguments = Bundle().apply { putString(ARG_PARAM_TYPE, type) }
        }
    }

    override fun intentParams(arguments: Bundle?, savedInstanceState: Bundle?) {
        super.intentParams(arguments, savedInstanceState)
        arguments?.let { type = it.getString(ARG_PARAM_TYPE) }
    }

    override fun bindView() {
        binding.status.setStatus(STATUS_LOADING)

        binding.rvContainer.layoutManager = LinearLayoutManager(context)
        binding.rvContainer.adapter = mAdapter.proxy
        mAdapter.openLoadMore()
        mAdapter.setAutoLoadMoreListener {
            type?.let { viewModel.loadListData(it, true) }
        }

        binding.refresh.setOnRefreshListener {
            type?.let { viewModel.loadListData(it, false) }
        }
    }

    override fun bindData() {
        viewModel.apply {
            contentListObservable.observe(this@ContentListFragment, Observer {
                executePageCompleted(it, mAdapter,
                    refreshBlock = {
                        binding.status.setStatus(STATUS_DATA)
                        binding.refresh.isRefreshing = false
                    }, emptyBlock = {
                        binding.status.setStatus(STATUS_EMPTY_DATA)
                        binding.refresh.isRefreshing = false
                    })
            })

            failedEvent.observe(this@ContentListFragment, Observer { it ->
                it.message?.let { toast(it) }
            })
        }
    }

    override fun lazyLoad() {
        type?.let { viewModel.loadListData(it, false) }
    }


}
