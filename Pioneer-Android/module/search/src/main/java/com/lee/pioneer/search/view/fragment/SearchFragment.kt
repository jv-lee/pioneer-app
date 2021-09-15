package com.lee.pioneer.search.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.page.submitData
import com.lee.library.adapter.page.submitFailed
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.utils.KeyboardUtil
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.service.DetailsService
import com.lee.pioneer.library.service.hepler.ModuleService
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
                KeyboardUtil.hideSoftInput(requireActivity())
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
                ModuleService.find<DetailsService>()
                    .navigationDetails(findNavController(), entity._id, KeyConstants.CONST_EMPTY)
            }
            setAutoLoadMoreListener {
                viewModel.searchDataList(true)
            }
        }

    }

    override fun bindData() {
        binding.vm = viewModel

        viewModel.apply {
            contentListObservable.observe(this@SearchFragment, {
                mAdapter.submitData(it)
            }, {
                mAdapter.submitFailed()
            })

            loadingObservable.observe(this@SearchFragment, Observer {
                mAdapter.initStatusView()
                mAdapter.pageLoading()
            })
        }
    }


}
