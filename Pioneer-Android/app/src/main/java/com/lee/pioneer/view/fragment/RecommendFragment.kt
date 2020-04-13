package com.lee.pioneer.view.fragment

import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentRecommendBinding
import com.lee.pioneer.databinding.LayoutRecommendHeaderBinding
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.tools.ViewTools
import com.lee.pioneer.view.adapter.ContentAdapter
import com.lee.pioneer.view.widget.BannerViewHolder
import com.lee.pioneer.viewmodel.RecommendViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 推荐页面
 */
class RecommendFragment :
    BaseNavigationFragment<FragmentRecommendBinding, RecommendViewModel>(
        R.layout.fragment_recommend,
        RecommendViewModel::class.java
    ) {

    private val headerBinding by lazy {
        DataBindingUtil.inflate<LayoutRecommendHeaderBinding>(
            layoutInflater, R.layout.layout_recommend_header, null, false
        )
    }
    private val mAdapter by lazy { ContentAdapter(context!!, ArrayList()) }

    override fun bindView() {
        //设置toolbar 搜索跳转
        binding.tvSearch.setOnClickListener {
            hideNavigation()
            findNavController().navigate(R.id.action_recommend_to_search)
        }

        //设置推荐头部 banner
        headerBinding.banner.setDelayedTime(5000)
        headerBinding.banner.setBannerPageClickListener { _, position ->
            (headerBinding.banner.data[position] as Banner).let {
                hideNavigation()
                findNavController().navigate(
                    RecommendFragmentDirections.actionRecommendToContentDetails(
                        KeyConstants.CONST_EMPTY,
                        it.url
                    )
                )
            }
        }

        //设置推荐头部 分类样式
        headerBinding.groupType.check(R.id.radio_view)
        ViewTools.setBackgroundSelectorTint(
            headerBinding.radioView,
            R.drawable.recommend_view_selector
        )
        ViewTools.setBackgroundSelectorTint(
            headerBinding.radioLike,
            R.drawable.recommend_like_selector
        )
        ViewTools.setBackgroundSelectorTint(
            headerBinding.radioComment,
            R.drawable.recommend_comment_selector
        )
        headerBinding.groupType.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_view -> viewModel.getContentList("views")
                R.id.radio_like -> viewModel.getContentList("likes")
                R.id.radio_comment -> viewModel.getContentList("comments")
            }
        }

        //设置数据列表
        binding.rvContainer.layoutManager = LinearLayoutManager(context)
        binding.rvContainer.adapter = mAdapter.proxy
        mAdapter.addHeader(headerBinding.root)
        mAdapter.notifyDataSetChanged()
    }

    override fun bindData() {
        viewModel.apply {
            bannerObservable.observe(this@RecommendFragment, Observer {
                headerBinding.banner.setPages(it) { BannerViewHolder() }
                headerBinding.banner.start()
            })

            contentObservable.observe(this@RecommendFragment, Observer {
                mAdapter.updateData(it)
                mAdapter.loadMoreEnd()
            })

            failedEvent.observe(this@RecommendFragment, Observer {
                it.message?.run { toast(this) }
            })
        }
    }

    override fun lazyLoad() {
        viewModel.getBannerData()
        viewModel.getContentList("views")
    }

    override fun onResume() {
        super.onResume()
        showNavigation()
        headerBinding.banner.pause()
        headerBinding.banner.start()
    }

    override fun onPause() {
        super.onPause()
        headerBinding.banner.pause()
    }

}
