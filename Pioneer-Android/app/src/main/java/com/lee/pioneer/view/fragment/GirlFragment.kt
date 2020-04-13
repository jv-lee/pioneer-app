package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.LeeViewAdapter
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.widget.StatusLayout
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentGirlBinding
import com.lee.pioneer.view.adapter.GirlAdapter
import com.lee.pioneer.viewmodel.GirlViewModel
import executePageCompleted

/**
 * @author jv.lee
 * @date 2020.4.10
 * @description 主页妹子板块
 */
class GirlFragment :
    BaseNavigationFragment<FragmentGirlBinding, GirlViewModel>(
        R.layout.fragment_girl,
        GirlViewModel::class.java
    ) {

    private val mAdapter by lazy { GirlAdapter(context!!, ArrayList()) }

    override fun bindView() {
        binding.rvContainer.layoutManager = LinearLayoutManager(context)
        binding.rvContainer.adapter = mAdapter.proxy

        mAdapter.openStatusView()
        mAdapter.updateStatus(LeeViewAdapter.STATUS_PAGE_LOADING)
        mAdapter.setOnItemClickListener { view, entity, position -> }
        mAdapter.setAutoLoadMoreListener {
            viewModel.getGirlContentData(true)
        }

        binding.refresh.setOnRefreshListener {
            viewModel.getGirlContentData(false)
        }
    }

    override fun bindData() {
        viewModel.apply {
            contentObservable.observe(this@GirlFragment, Observer {
                executePageCompleted(it, mAdapter,
                    {
                        binding.refresh.isRefreshing = false
                    }, {
                        binding.refresh.isRefreshing = false
                        mAdapter.updateStatus(LeeViewAdapter.STATUS_PAGE_EMPTY)
                    })
            })

            failedEvent.observe(this@GirlFragment, Observer { it ->
                it?.message?.let {
                    toast(it)
                    mAdapter.updateStatus(LeeViewAdapter.STATUS_PAGE_ERROR)
                }
            })
        }
    }

    override fun lazyLoad() {
        viewModel.getGirlContentData(false)
    }

    override fun onResume() {
        super.onResume()
        showNavigation()
    }

}