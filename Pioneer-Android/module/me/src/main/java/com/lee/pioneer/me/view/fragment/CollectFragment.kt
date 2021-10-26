package com.lee.pioneer.me.view.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.page.submitData
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.extensions.binding
import com.lee.library.extensions.toast
import com.lee.library.mvvm.livedata.LoadStatus
import com.lee.library.mvvm.ui.observeState
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.entity.ContentHistory
import com.lee.pioneer.library.common.entity.PageData
import com.lee.pioneer.me.R
import com.lee.pioneer.me.adapter.ContentChildAdapter
import com.lee.pioneer.me.databinding.FragmentCollectBinding
import com.lee.pioneer.me.viewmodel.CollectViewModel
import com.lee.pioneer.router.navigateDetails

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description MeFragment ChildPage -> 收藏页面
 */
class CollectFragment :
    BaseNavigationFragment(R.layout.fragment_collect) {

    private val binding by binding(FragmentCollectBinding::bind)
    private val viewModel by viewModels<CollectViewModel>()
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
            contentLive.observeState<PageData<ContentHistory>>(this@CollectFragment, success = {
                mAdapter.submitData(it, limit = 0)
            }, error = {
                toast(it.message)
            })

            viewModel.loadHistory(LoadStatus.INIT)
        }
    }

}
