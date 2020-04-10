package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.status.setStatus(StatusLayout.STATUS_LOADING)

        binding.rvContainer.layoutManager = LinearLayoutManager(context)
        binding.rvContainer.adapter = mAdapter.proxy

        mAdapter.openLoadMore()
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
                        binding.status.setStatus(StatusLayout.STATUS_DATA)
                        binding.refresh.isRefreshing = false
                    }, {
                        binding.status.setStatus(StatusLayout.STATUS_EMPTY_DATA)
                        binding.refresh.isRefreshing = false
                    })
            })

            failedEvent.observe(this@GirlFragment, Observer { it ->
                it?.message?.let {
                    toast(it)
                    binding.status.setStatus(StatusLayout.STATUS_DATA_ERROR)
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