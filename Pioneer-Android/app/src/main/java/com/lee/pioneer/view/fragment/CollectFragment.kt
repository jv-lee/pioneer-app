package com.lee.pioneer.view.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.extensions.binding
import com.lee.library.mvvm.load.LoadStatus
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentCollectBinding
import com.lee.pioneer.view.adapter.ContentChildAdapter
import com.lee.pioneer.viewmodel.CollectViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description MeFragment ChildPage -> 收藏页面
 */
class CollectFragment :
    BaseNavigationFragment(R.layout.fragment_collect) {

    private val binding by binding(FragmentCollectBinding::bind)
    private val viewModel by viewModels<CollectViewModel>()
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
                    CollectFragmentDirections.actionCollectToContentDetails(
                        entity.content._id, KeyConstants.CONST_EMPTY
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
