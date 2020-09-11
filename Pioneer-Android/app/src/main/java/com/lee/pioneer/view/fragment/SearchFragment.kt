package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.extensions.glideEnable
import com.lee.library.utils.KeyboardUtil
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentSearchBinding
import com.lee.pioneer.view.adapter.ContentAdapter
import com.lee.pioneer.viewmodel.SearchViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 搜索页
 */
class SearchFragment :
    BaseNavigationFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    private val mAdapter by lazy { ContentAdapter(requireContext(), ArrayList()) }

    override fun bindView() {
        binding.run {
            tvCancel.setOnClickListener {
                KeyboardUtil.hideSoftInput(requireActivity())
                findNavController().popBackStack()
            }

            rvContainer.run {
                glideEnable()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter.proxy
            }
        }

        mAdapter.run {
            initStatusView()
            setOnItemClickListener { _, entity, _ ->
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchToContentDetails(
                        entity._id,
                        KeyConstants.CONST_EMPTY
                    )
                )
            }
            setAutoLoadMoreListener {
                viewModel.searchDataList(true)
            }
        }

    }

    override fun bindData() {
        binding.vm = viewModel

        viewModel.apply {
            contentListObservable.observe(this@SearchFragment, Observer {
                mAdapter.submitData(it)
            })

            //错误处理
            failedEvent.observe(this@SearchFragment, Observer { it ->
                it?.message?.let { toast(it) }
                when (it.code) {
                    -1 -> mAdapter.submitFailed()
                }
            })

            loadingObservable.observe(this@SearchFragment, Observer {
                mAdapter.initStatusView()
                mAdapter.pageLoading()
            })
        }
    }


}
