package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
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
        mAdapter.pageLoading()
        mAdapter.setOnItemClickListener { view, entity, position ->
            hideNavigation()
            findNavController().navigate(
                GirlFragmentDirections.actionGirlToContentDetails(
                    entity._id,
                    KeyConstants.CONST_EMPTY
                )
            )
        }
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
                executePageCompleted(it, mAdapter, binding.refresh)
            })

            //错误处理
            failedEvent.observe(this@GirlFragment, Observer { it ->
                it?.message?.let { toast(it) }
                when (it.code) {
                    -1 -> {
                        if (mAdapter.isPageCompleted) {
                            mAdapter.loadFailed()
                        } else {
                            mAdapter.pageError()
                        }
                    }
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