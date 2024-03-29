package com.lee.pioneer.home.view.fragment

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
import com.lee.library.mvvm.livedata.LoadStatus
import com.lee.library.mvvm.ui.observeState
import com.lee.library.net.HttpManager
import com.lee.library.tools.DarkViewUpdateTools
import com.lee.pioneer.home.R
import com.lee.pioneer.home.databinding.FragmentContentListBinding
import com.lee.pioneer.home.view.adapter.ContentAdapter
import com.lee.pioneer.home.viewmodel.ContentListViewModel
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.entity.PageData
import com.lee.pioneer.router.navigateDetails

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
                findNavController().navigateDetails(entity._id, entity.url)
            }
        }
    }

    override fun bindData() {
        viewModel.run {
            //列表数据更新
            contentListLive.observeState<PageData<Content>>(this@ContentListFragment, success = {
                binding.refresh.isRefreshing = false
                mAdapter.submitData(it, diff = true)
            }, error = {
                toast(HttpManager.getInstance().getServerMessage(it))
                binding.refresh.isRefreshing = false
                mAdapter.submitFailed()
            })
        }
    }

    override fun lazyLoad() {
        viewModel.loadListData(LoadStatus.INIT, type)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateDarkView() {
        mAdapter.reInitStatusView()
        mAdapter.notifyDataSetChanged()
    }

}
