package com.lee.pioneer.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.extensions.glideEnable
import com.lee.pioneer.MainFragmentDirections
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants.Companion.CONST_EMPTY
import com.lee.pioneer.databinding.FragmentContentListBinding
import com.lee.pioneer.view.adapter.ContentAdapter
import com.lee.pioneer.viewmodel.ContentListViewModel
import executePageCompleted
import executePageError

private const val ARG_PARAM_TYPE = "arg_param_type"

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 内容列表页
 */
class ContentListFragment :
    BaseNavigationFragment<FragmentContentListBinding, ContentListViewModel>(R.layout.fragment_content_list) {
    private var type: String? = null
    private val mAdapter by lazy { ContentAdapter(context!!, ArrayList()) }

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
        //设置view
        binding.run {
            rvContainer.run {
                glideEnable()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter.proxy
            }

            refresh.setOnRefreshListener {
                mAdapter.openLoadMore()
                type?.let { viewModel.loadListData(it, isRefresh = true) }
            }

        }

        //设置adapter
        mAdapter.run {
            initStatusView()
            pageLoading()
            setAutoLoadMoreListener { type?.let { viewModel.loadListData(it, isLoadMore = true) } }
            setLoadErrorListener(object : LoadErrorListener {
                override fun itemReload() {
                    type?.let { viewModel.loadListData(it, isReLoad = true) }
                }

                override fun pageReload() {
                    type?.let { viewModel.loadListData(it) }
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
                executePageCompleted(it, mAdapter, binding.refresh, diff = true)
            })

            //错误处理
            contentListData.failedEvent.observe(this@ContentListFragment, Observer { it ->
                it?.message?.let { toast(it) }
                when (it.code) {
                    -1 -> executePageError(mAdapter, binding.refresh)
                }
            })

            //首个tab页面默认加载
            type?.let { if (type.equals("Android")) loadListData(it) }
        }
    }

    override fun lazyLoad() {
        //非首个tab页面使用懒加载
        type?.let { if (!type.equals("Android")) viewModel.loadListData(it) }
    }

}
