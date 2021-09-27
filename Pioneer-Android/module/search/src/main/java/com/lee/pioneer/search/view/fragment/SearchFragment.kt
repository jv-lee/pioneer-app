package com.lee.pioneer.search.view.fragment

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.adapter.page.submitData
import com.lee.library.adapter.page.submitFailed
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.mvvm.ui.observe
import com.lee.library.tools.KeyboardTools
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.entity.PageData
import com.lee.pioneer.router.navigateDetails
import com.lee.pioneer.search.R
import com.lee.pioneer.search.databinding.FragmentSearchBinding
import com.lee.pioneer.search.view.adapter.ContentAdapter
import com.lee.pioneer.search.viewmodel.SearchViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 搜索页
 */
class SearchFragment :
    BaseVMNavigationFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    private val mAdapter by lazy { ContentAdapter(requireContext(), arrayListOf()) }

    override fun bindView() {
        binding.run {
            tvCancel.setOnClickListener {
                KeyboardTools.hideSoftInput(requireActivity())
                findNavController().popBackStack()
            }

            rvContainer.run {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter.proxy
            }
        }

        mAdapter.run {
            initStatusView()
            setOnItemClickListener { _, entity, _ ->
                findNavController().navigateDetails(entity._id, KeyConstants.CONST_EMPTY)
            }
            setAutoLoadMoreListener {
                viewModel.pageLive.loadMore()
            }
            setLoadErrorListener(object : LoadErrorListener {
                override fun pageReload() {
                    viewModel.pageLive.refresh()
                }

                override fun itemReload() {
                    viewModel.pageLive.reload()
                }
            })
        }

    }

    override fun bindData() {
        binding.vm = viewModel

        viewModel.apply {
            contentLive.observe<PageData<Content>>(this@SearchFragment, success = {
                mAdapter.submitData(it)
            }, error = {
                mAdapter.submitFailed()
            })

            loadingLive.observe(this@SearchFragment, {
                mAdapter.initStatusView()
                mAdapter.pageLoading()
            })
        }
    }


}
