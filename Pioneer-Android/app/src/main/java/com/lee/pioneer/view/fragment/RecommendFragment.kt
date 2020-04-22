package com.lee.pioneer.view.fragment

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.extensions.glideEnable
import com.lee.library.extensions.setButtonTint
import com.lee.pioneer.MainFragmentDirections
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentRecommendBinding
import com.lee.pioneer.databinding.LayoutRecommendHeaderBinding
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.view.adapter.ContentAdapter
import com.lee.pioneer.view.adapter.resource.RecommendLoadResource
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
            findNavController().navigate(R.id.action_main_to_search)
        }

        //设置推荐头部 banner
        headerBinding.banner.setDelayedTime(5000)
        headerBinding.banner.setBannerPageClickListener { _, position ->
            (headerBinding.banner.data[position] as Banner).let {
                findNavController().navigate(
                    MainFragmentDirections.actionMainToContentDetails(
                        KeyConstants.CONST_EMPTY,
                        it.url
                    )
                )
            }
        }

        //设置推荐头部 分类样式
        headerBinding.groupType.check(R.id.radio_view)
        headerBinding.radioView.setButtonTint(
            R.drawable.vector_view,
            R.drawable.recommend_view_selector
        )
        headerBinding.radioLike.setButtonTint(
            R.drawable.vector_like,
            R.drawable.recommend_like_selector
        )
        headerBinding.radioComment.setButtonTint(
            R.drawable.vector_comment,
            R.drawable.recommend_comment_selector
        )
        headerBinding.groupType.setOnCheckedChangeListener { group, checkedId ->
            mAdapter.initStatusView()
            mAdapter.pageLoading()
            when (checkedId) {
                R.id.radio_view -> viewModel.getContentList("views")
                R.id.radio_like -> viewModel.getContentList("likes")
                R.id.radio_comment -> viewModel.getContentList("comments")
            }
        }

        //设置数据列表
        binding.rvContainer.glideEnable()
        binding.rvContainer.layoutManager = LinearLayoutManager(context)
        binding.rvContainer.adapter = mAdapter.proxy

        mAdapter.setLoadResource(RecommendLoadResource())
        mAdapter.initStatusView()
        mAdapter.pageLoading()
        mAdapter.addHeader(headerBinding.root)
        mAdapter.notifyDataSetChanged()
        mAdapter.setOnItemClickListener { view, entity, position ->
            viewModel.insertContentHistoryToDB(entity)
            findNavController().navigate(
                MainFragmentDirections.actionMainToContentDetails(
                    entity._id,
                    KeyConstants.CONST_EMPTY
                )
            )
        }
    }

    override fun bindData() {
        viewModel.apply {
            bannerObservable.observe(this@RecommendFragment, Observer {
                headerBinding.banner.setPages(it.toList()) { BannerViewHolder() }
                headerBinding.banner.start()
            })

            contentObservable.observe(this@RecommendFragment, Observer {
                if (it.isNullOrEmpty()) {
                    mAdapter.pageEmpty()
                } else {
                    if (mAdapter.data != it) {
                        mAdapter.pageCompleted()
                        mAdapter.updateData(it)
                        mAdapter.loadMoreEnd()
                    }
                }

            })

            failedEvent.observe(this@RecommendFragment, Observer {
                it.message?.run { toast(this) }
                when (it.code) {
                    -1 -> mAdapter.pageError()
                }
            })
        }
    }

    override fun lazyLoad() {
        viewModel.getBannerData()
        viewModel.getContentList("views")
    }

    override fun onResume() {
        super.onResume()
        headerBinding.banner.pause()
        headerBinding.banner.start()
    }

    override fun onPause() {
        super.onPause()
        headerBinding.banner.pause()
    }

}
