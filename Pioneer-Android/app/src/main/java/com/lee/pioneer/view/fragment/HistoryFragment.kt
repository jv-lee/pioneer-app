package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.mvvm.load.LoadStatus
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentHistoryBinding
import com.lee.pioneer.view.adapter.ContentChildAdapter
import com.lee.pioneer.viewmodel.HistoryViewModel

/**
 * @author jv.lee
 * @date 2020/4/17
 * @description MeFragment ChildPage -> 浏览记录页面
 */
class HistoryFragment :
    BaseVMNavigationFragment<FragmentHistoryBinding, HistoryViewModel>(R.layout.fragment_history) {

    private val mAdapter by lazy { ContentChildAdapter(requireContext(), ArrayList()) }

    override fun bindView() {
        binding.rvContainer.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter.proxy
        }

        mAdapter.run {
            initStatusView()
            pageLoading()
            setAutoLoadMoreListener { viewModel.loadHistory(LoadStatus.LOAD_MORE) }
            setOnItemClickListener { _, entity, _ ->
                findNavController().navigate(
                    HistoryFragmentDirections.actionHistoryToContentDetails(
                        entity.content._id, KeyConstants.CONST_EMPTY
                    )
                )
            }
        }
    }

    override fun bindData() {
        viewModel.run {
            contentData.observe(this@HistoryFragment, {
                mAdapter.submitData(it, limit = 0)
            }, {
                toast(it)
            })

            loadHistory(LoadStatus.INIT)
        }
    }

}
