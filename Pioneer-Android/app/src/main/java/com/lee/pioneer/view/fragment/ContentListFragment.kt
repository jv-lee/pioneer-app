package com.lee.pioneer.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.base.BaseNavigationFragment
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
    BaseNavigationFragment<FragmentContentListBinding, ContentListViewModel>(R.layout.fragment_content_list),
    DarkViewUpdateTools.ViewCallback {
    private var type: String? = null
    private val mAdapter by lazy { ContentAdapter(requireContext(), ArrayList()) }

    companion object {
        @JvmStatic
        fun newInstance(type: String) = ContentListFragment().apply {
            arguments = Bundle().apply { putString(ARG_PARAM_TYPE, type) }
        }
    }

    override fun intentParams(arguments: Bundle?, savedInstanceState: Bundle?) {
        super.intentParams(arguments, savedInstanceState)
        arguments?.let { type = it.getString(ARG_PARAM_TYPE) }
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
                type?.let { viewModel.loadListData(LoadStatus.REFRESH, it) }
            }

        }

        //设置adapter
        mAdapter.run {
            initStatusView()
            pageLoading()
            setAutoLoadMoreListener {
                type?.let { viewModel.loadListData(LoadStatus.LOAD_MORE, it) }
            }
            setLoadErrorListener(object : LoadErrorListener {
                override fun itemReload() {
                    type?.let { viewModel.loadListData(LoadStatus.RELOAD, it) }
                }

                override fun pageReload() {
                    type?.let { viewModel.loadListData(LoadStatus.REFRESH, it) }
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
            contentListData.observe(this@ContentListFragment, Observer {
                binding.refresh.isRefreshing = false
                mAdapter.submitData(it, diff = true)
            }, Observer {
                toast(it)
                binding.refresh.isRefreshing = false
                mAdapter.submitFailed()
            })

            //首个tab页面默认加载
            type?.let { if (type.equals("Android")) viewModel.loadListData(LoadStatus.INIT, it) }
        }
    }

    override fun lazyLoad() {
        //非首个tab页面使用懒加载
        type?.let { if (!type.equals("Android")) viewModel.loadListData(LoadStatus.INIT, it) }
    }

    override fun updateDarkView() {
        mAdapter.notifyDataSetChanged()
    }

}
