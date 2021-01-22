package com.lee.pioneer.view.fragment

import android.annotation.SuppressLint
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.extensions.glideEnable
import com.lee.library.extensions.setBackgroundAlphaCompat
import com.lee.library.extensions.setScrollTransparent
import com.lee.library.mvvm.load.LoadStatus
import com.lee.library.utils.TimeUtil
import com.lee.pioneer.MainFragmentDirections
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentGirlBinding
import com.lee.pioneer.databinding.LayoutGirlHeaderBinding
import com.lee.pioneer.tools.ViewTools
import com.lee.pioneer.view.adapter.GirlAdapter
import com.lee.pioneer.viewmodel.GirlViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author jv.lee
 * @date 2020.4.10
 * @description 主页妹子板块
 */
class GirlFragment :
    BaseNavigationFragment<FragmentGirlBinding, GirlViewModel>(R.layout.fragment_girl) {

    private val linearLayoutManager by lazy { LinearLayoutManager(context) }
    private val mAdapter by lazy { GirlAdapter(requireContext(), ArrayList()) }
    private val headerViewBinding by lazy {
        DataBindingUtil.inflate<LayoutGirlHeaderBinding>(
            layoutInflater, R.layout.layout_girl_header, null, false
        )
    }

    override fun bindView() {
        //设置头部view padding值
        headerViewBinding.root.setPadding(0, binding.statusBar.getToolbarLayoutHeight(), 0, 0)

        binding.run {
            //设置statusBar透明度
            statusBar.background?.let { it.mutate().alpha = 0 }
            ViewCompat.setElevation(statusBar, 1f)

            //设置列表数据项
            rvContainer.run {
                glideEnable()
//                layoutAnimation = ViewTools.getItemOrderAnimator(requireContext())
                layoutManager = linearLayoutManager
                adapter = mAdapter.proxy
                //设置滑动设置statusBar透明度
                setScrollTransparent { transition, alpha -> statusBar.setBackgroundAlphaCompat(alpha) }
            }

            //刷新回调
            refresh.setOnRefreshListener {
                mAdapter.openLoadMore()
                viewModel.getGirlContentData(LoadStatus.REFRESH)
            }

        }


        //适配器参数设置
        mAdapter.run {
            initStatusView()
            pageLoading()
            addHeader(headerViewBinding.root)
            setOnItemClickListener { _, entity, _ ->
                viewModel.insertContentHistoryToDB(entity)
                findNavController().navigate(
                    MainFragmentDirections.actionMainToContentDetails(
                        entity._id,
                        KeyConstants.CONST_EMPTY
                    )
                )
            }
            setAutoLoadMoreListener {
                viewModel.getGirlContentData(LoadStatus.LOAD_MORE)
            }
            setLoadErrorListener(object : LoadErrorListener {
                override fun itemReload() {
                    viewModel.getGirlContentData(LoadStatus.RELOAD)
                }

                override fun pageReload() {
                    viewModel.getGirlContentData(LoadStatus.REFRESH)
                }

            })

        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun bindData() {
        //设置header时间
        headerViewBinding.tvDate.text = TimeUtil.getCurTimeString(SimpleDateFormat("MM月dd日"))
        headerViewBinding.tvWeek.text = TimeUtil.getWeek(Date())

        viewModel.run {
            contentData.observe(this@GirlFragment, Observer {
                binding.refresh.isRefreshing = false
                mAdapter.submitData(it, diff = true)
            }, Observer {
                toast(it)
                binding.refresh.isRefreshing = false
                mAdapter.submitFailed()
            })

        }
    }

    override fun lazyLoad() {
        viewModel.getGirlContentData(LoadStatus.INIT)
    }

}