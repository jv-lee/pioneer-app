package com.lee.pioneer.view.fragment

import android.annotation.SuppressLint
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.TimeUtil
import com.lee.pioneer.MainFragmentDirections
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentGirlBinding
import com.lee.pioneer.databinding.LayoutGirlHeaderBinding
import com.lee.pioneer.view.adapter.GirlAdapter
import com.lee.pioneer.viewmodel.GirlViewModel
import executePageCompleted
import executePageError
import glideEnable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

/**
 * @author jv.lee
 * @date 2020.4.10
 * @description 主页妹子板块
 */
class GirlFragment :
    BaseNavigationFragment<FragmentGirlBinding, GirlViewModel>(
        R.layout.fragment_girl,
        GirlViewModel::class.java
    ) {

    private val linearLayoutManager by lazy { LinearLayoutManager(context) }
    private val mAdapter by lazy { GirlAdapter(context!!, ArrayList()) }
    private val headerViewBinding by lazy {
        DataBindingUtil.inflate<LayoutGirlHeaderBinding>(
            layoutInflater, R.layout.layout_girl_header, null, false
        )
    }

    override fun bindView() {
        //设置头部view padding值
        headerViewBinding.root.setPadding(0, binding.statusBar.getToolbarLayoutHeight(), 0, 0)
        //设置statusBar透明度
        binding.statusBar.background.mutate().alpha = 0
        ViewCompat.setElevation(binding.statusBar, 1f)

        //设置列表数据项
        binding.rvContainer.glideEnable()
        binding.rvContainer.layoutManager = linearLayoutManager
        binding.rvContainer.adapter = mAdapter.proxy
        //设置滑动设置statusBar透明度
        binding.rvContainer.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position = linearLayoutManager.findFirstVisibleItemPosition()
                if (position == 0) {
                    linearLayoutManager.findViewByPosition(position)?.let {
                        val scale = (255.0 / it.height)
                        binding.statusBar.background.mutate().alpha = (abs(it.top) * scale).toInt()
                    }
                } else {
                    binding.statusBar.background.mutate().alpha = 255
                }
            }
        })

        //适配器参数设置
        mAdapter.initStatusView()
        mAdapter.pageLoading()
        mAdapter.addHeader(headerViewBinding.root)
        mAdapter.setOnItemClickListener { view, entity, position ->
            viewModel.insertContentHistoryToDB(entity)
            findNavController().navigate(
                MainFragmentDirections.actionMainToContentDetails(
                    entity._id,
                    KeyConstants.CONST_EMPTY
                )
            )
        }
        mAdapter.setAutoLoadMoreListener {
            viewModel.getGirlContentData(true)
        }

        binding.refresh.setOnRefreshListener {
            mAdapter.openLoadMore()
            viewModel.getGirlContentData(false)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun bindData() {
        //设置header时间
        headerViewBinding.tvDate.text = TimeUtil.getCurTimeString(SimpleDateFormat("MM月dd日"))
        headerViewBinding.tvWeek.text = TimeUtil.getWeek(Date())

        viewModel.apply {
            contentObservable.observe(this@GirlFragment, Observer {
                executePageCompleted(it, mAdapter, binding.refresh)
            })

            //错误处理
            failedEvent.observe(this@GirlFragment, Observer { it ->
                it?.message?.let { toast(it) }
                when (it.code) {
                    -1 -> executePageError(mAdapter, binding.refresh)
                }
            })

        }
    }

    override fun lazyLoad() {
        viewModel.getGirlContentData(false)
    }

}