package com.lee.pioneer.me.view.fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.page.submitData
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.extensions.binding
import com.lee.library.extensions.toast
import com.lee.library.mvvm.load.LoadStatus
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.me.adapter.ContentChildAdapter
import com.lee.pioneer.me.databinding.FragmentCollectBinding
import com.lee.pioneer.me.viewmodel.CollectViewModel
import com.lee.pioneer.me.R as MR

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description MeFragment ChildPage -> 收藏页面
 */
class CollectFragment :
    BaseNavigationFragment(MR.layout.fragment_collect) {

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
                findNavController().navigate(
                 MR.id.action_collect_to_contentDetails   ,
                    bundleOf(
                        Pair(KeyConstants.KEY_ID, entity.content._id),
                        Pair(KeyConstants.KEY_URL, KeyConstants.CONST_EMPTY)
                    )
                )
            }
        }
    }

    override fun bindData() {
        viewModel.run {
            contentData.observe(this@CollectFragment, {
                mAdapter.submitData(it, limit = 0)
            }, {
                toast(it)
            })

            viewModel.loadHistory(LoadStatus.INIT)
        }
    }

}
