package com.lee.pioneer.girl

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.adapter.page.submitData
import com.lee.library.adapter.page.submitFailed
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.extensions.*
import com.lee.library.mvvm.load.LoadStatus
import com.lee.library.utils.TimeUtil
import com.lee.pioneer.R
import com.lee.pioneer.girl.adapter.GirlAdapter
import com.lee.pioneer.girl.databinding.FragmentGirlBinding
import com.lee.pioneer.girl.databinding.LayoutGirlHeaderBinding
import com.lee.pioneer.girl.viewmodel.GirlViewModel
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.tools.DarkViewUpdateTools
import java.text.SimpleDateFormat
import java.util.*
import com.lee.pioneer.girl.R as GR

/**
 * @author jv.lee
 * @date 2020.4.10
 * @description 主页妹子板块
 */
class GirlFragment :
    BaseVMNavigationFragment<FragmentGirlBinding, GirlViewModel>(GR.layout.fragment_girl),
    DarkViewUpdateTools.ViewCallback {

    private val mAdapter by lazy { GirlAdapter(requireContext(), arrayListOf()) }

    private val linearLayoutManager by lazy { LinearLayoutManager(context) }

    private val headerViewBinding by lazy {
        DataBindingUtil.inflate<LayoutGirlHeaderBinding>(
            layoutInflater, GR.layout.layout_girl_header, null, false
        )
    }

    override fun bindView() {
        DarkViewUpdateTools.bindViewCallback(this, this)
        //设置头部view padding值
        headerViewBinding.root.setPadding(0, binding.statusBar.getToolbarLayoutHeight(), 0, 0)

        binding.run {
            //设置statusBar透明度
            statusBar.background?.let { it.mutate().alpha = 0 }
            ViewCompat.setElevation(statusBar, 1f)

            //设置列表数据项
            rvContainer.run {
//                glideEnable()
//                layoutAnimation = ViewTools.getItemOrderAnimator(requireContext())
                layoutManager = linearLayoutManager
                adapter = mAdapter.proxy
                //设置滑动设置statusBar透明度
                setScrollTransparent { _, alpha -> statusBar.setBackgroundAlphaCompat(alpha) }
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
                findNavController().navigate(GR.id.action_girl_to_details,
                    bundleOf(
                        Pair(KeyConstants.KEY_ID, entity._id,),
                        Pair(KeyConstants.KEY_URL, KeyConstants.CONST_EMPTY)
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
            contentData.observe(this@GirlFragment, {
                binding.refresh.isRefreshing = false
                mAdapter.submitData(it, diff = true)
            }, {
                toast(it)
                binding.refresh.isRefreshing = false
                mAdapter.submitFailed()
            })

        }
    }

    override fun lazyLoad() {
        viewModel.getGirlContentData(LoadStatus.INIT)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateDarkView() {
        val alpha = binding.statusBar.getBackgroundAlphaCompat()
        binding.statusBar.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.statusBar.setBackgroundAlphaCompat(alpha)

        binding.constContainer.setBackgroundColorCompat(R.color.colorThemeBackground)

        headerViewBinding.tvWeek.setTextColorCompat(R.color.colorThemePrimaryDark)
        headerViewBinding.tvDate.setTextColorCompat(R.color.colorThemePrimaryDark)
        headerViewBinding.tvToday.setTextColorCompat(R.color.colorThemeAccent)

        mAdapter.reInitStatusView()
        mAdapter.notifyDataSetChanged()
    }

}