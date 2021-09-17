package com.lee.pioneer.me.view.fragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.page.submitData
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.extensions.toast
import com.lee.library.mvvm.load.LoadStatus
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.me.R
import com.lee.pioneer.me.adapter.ContentChildAdapter
import com.lee.pioneer.me.databinding.FragmentHistoryBinding
import com.lee.pioneer.me.viewmodel.HistoryViewModel
import com.lee.pioneer.router.navigateDetails

/**
 * @author jv.lee
 * @date 2020/4/17
 * @description MeFragment ChildPage -> 浏览记录页面
 */
class HistoryFragment :
    BaseVMNavigationFragment<FragmentHistoryBinding, HistoryViewModel>(R.layout.fragment_history) {

    private val mAdapter by lazy { ContentChildAdapter(requireContext(), arrayListOf()) }

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
                findNavController().navigateDetails(entity.content._id, KeyConstants.CONST_EMPTY)
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
