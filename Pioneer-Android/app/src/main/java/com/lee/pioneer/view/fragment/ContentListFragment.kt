package com.lee.pioneer.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.adapter.page.submitData
import com.lee.library.adapter.page.submitFailed
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.extensions.arguments
import com.lee.library.extensions.toast
import com.lee.library.mvvm.load.LoadStatus
import com.lee.pioneer.MainFragmentDirections
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants.Companion.CONST_EMPTY
import com.lee.pioneer.databinding.FragmentContentListBinding
import com.lee.pioneer.tools.DarkViewUpdateTools
import com.lee.pioneer.view.adapter.ContentAdapter
import com.lee.pioneer.viewmodel.ContentListViewModel

private const val ARG_PARAM_TYPE = "arg_param_type"

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 内容列表页
 */
class ContentListFragment :
    BaseVMNavigationFragment<FragmentContentListBinding, ContentListViewModel>(R.layout.fragment_content_list),
    DarkViewUpdateTools.ViewCallback {
    private val type by arguments<String>(ARG_PARAM_TYPE)
    private val mAdapter by lazy { ContentAdapter(requireContext(), arrayListOf()) }

    companion object {
        @JvmStatic
        fun newInstance(type: String) = ContentListFragment().apply {
            arguments = Bundle().apply { putString(ARG_PARAM_TYPE, type) }
        }
    }

    override fun bindView() {
        DarkViewUpdateTools.bindViewCallback(this, this)
        //设置view
        binding.run {
            rvContainer.run {
//                layoutAnimation = ViewTools.getItemOrderAnimator(requireContext())
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter.proxy
            }

            refresh.setOnRefreshListener {
                mAdapter.openLoadMore()
                viewModel.loadListData(LoadStatus.REFRESH, type)
            }

        }

        //设置adapter
        mAdapter.run {
            initStatusView()
            pageLoading()
            setAutoLoadMoreListener {
                viewModel.loadListData(LoadStatus.LOAD_MORE, type)
            }
            setLoadErrorListener(object : LoadErrorListener {
                override fun itemReload() {
                    viewModel.loadListData(LoadStatus.RELOAD, type)
                }

                override fun pageReload() {
                    viewModel.loadListData(LoadStatus.REFRESH, type)
                }
            })
            setOnItemClickListener { _, entity, _ ->
                viewModel.insertContentHistoryToDB(entity)
                findNavController().navigate(
                    MainFragmentDirections.actionMainToContentDetails(entity._id, CONST_EMPTY)
                )
            }
        }
    }

    override fun bindData() {
        viewModel.run {
            //列表数据更新
            contentListData.observe(this@ContentListFragment, {
                binding.refresh.isRefreshing = false
                mAdapter.submitData(it, diff = true)
            }, {
                toast(it)
                binding.refresh.isRefreshing = false
                mAdapter.submitFailed()
            })

            //首个tab页面默认加载
            if (type == "Android") viewModel.loadListData(LoadStatus.INIT, type)
        }
    }

    override fun lazyLoad() {
        //非首个tab页面使用懒加载
        if (type != "Android") viewModel.loadListData(LoadStatus.INIT, type)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateDarkView() {
        mAdapter.reInitStatusView()
        mAdapter.notifyDataSetChanged()
    }

}
