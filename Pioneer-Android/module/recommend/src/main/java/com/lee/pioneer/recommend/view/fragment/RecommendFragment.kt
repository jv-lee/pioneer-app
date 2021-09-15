package com.lee.pioneer.recommend.view.fragment

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.extensions.*
import com.lee.library.tools.DarkViewUpdateTools
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.entity.Banner
import com.lee.pioneer.library.service.DetailsService
import com.lee.pioneer.library.service.SearchService
import com.lee.pioneer.library.service.hepler.ModuleService
import com.lee.pioneer.recommend.R
import com.lee.pioneer.recommend.databinding.FragmentRecommendBinding
import com.lee.pioneer.recommend.databinding.LayoutRecommendHeaderBinding
import com.lee.pioneer.recommend.view.adapter.ContentAdapter
import com.lee.pioneer.recommend.view.widget.BannerViewHolder
import com.lee.pioneer.recommend.view.widget.RecommendLoadResource
import com.lee.pioneer.recommend.viewmodel.RecommendViewModel
import java.util.*

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 推荐页面
 */
class RecommendFragment :
    BaseVMNavigationFragment<FragmentRecommendBinding, RecommendViewModel>(R.layout.fragment_recommend),
    DarkViewUpdateTools.ViewCallback {

    private val headerBinding by lazy {
        DataBindingUtil.inflate<LayoutRecommendHeaderBinding>(
            layoutInflater, R.layout.layout_recommend_header, null, false
        )
    }
    private val mAdapter by lazy { ContentAdapter(requireContext(), arrayListOf()) }
    private var type = "views"

    @SuppressLint("NotifyDataSetChanged")
    override fun bindView() {
        DarkViewUpdateTools.bindViewCallback(this, this)

        //设置toolbar 搜索跳转
        binding.tvSearch.setOnClickListener {
            ModuleService.find<SearchService>().navigationSearch(findNavController())
        }

        headerBinding.run {
            //设置推荐头部 banner
            banner.setDelayedTime(5000)
            banner.setBannerPageClickListener { _, position ->
                (banner.data[position] as Banner).let {
                    ModuleService.find<DetailsService>()
                        .navigationDetails(findNavController(), KeyConstants.CONST_EMPTY, it.url)
                }
            }

            //设置推荐头部 分类样式
            groupType.check(R.id.radio_view)
            radioView.setButtonTint(
                R.drawable.vector_view,
                R.drawable.recommend_view_selector
            )
            radioLike.setButtonTint(
                R.drawable.vector_like,
                R.drawable.recommend_like_selector
            )
            radioComment.setButtonTint(
                R.drawable.vector_comment,
                R.drawable.recommend_comment_selector
            )
            groupType.setOnCheckedChangeListener { _, checkedId ->
                mAdapter.initStatusView()
                mAdapter.pageLoading()
                when (checkedId) {
                    R.id.radio_view -> {
                        type = "views"
                        viewModel.getContentList(type)
                    }
                    R.id.radio_like -> {
                        type = "likes"
                        viewModel.getContentList(type)
                    }
                    R.id.radio_comment -> {
                        type = "comments"
                        viewModel.getContentList(type)
                    }
                }
            }

        }

        //设置数据列表
        binding.rvContainer.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter.proxy
        }

        mAdapter.run {
            setLoadResource(RecommendLoadResource())
            initStatusView()
            pageLoading()
            addHeader(headerBinding.root)
            notifyDataSetChanged()
            setOnItemClickListener { _, entity, _ ->
                viewModel.insertContentHistoryToDB(entity)
                ModuleService.find<DetailsService>()
                    .navigationDetails(findNavController(), entity._id, KeyConstants.CONST_EMPTY)
            }
            setLoadErrorListener(object : LoadErrorListener {
                override fun itemReload() {}

                override fun pageReload() {
                    viewModel.getContentList(type)
                }

            })
        }

    }

    override fun bindData() {
        viewModel.apply {
            bannerData.observe(this@RecommendFragment, {
                headerBinding.banner.setPages(it.toList()) { BannerViewHolder() }
                headerBinding.banner.start()
            }, {
                toast(it)
            })

            contentData.observe(this@RecommendFragment, {
                if (it.data.isNullOrEmpty() && mAdapter.data.isNullOrEmpty()) {
                    mAdapter.pageEmpty()
                } else if (it.data.isNotEmpty()) {
                    mAdapter.updateData(it.data)
                    mAdapter.pageCompleted()
                    mAdapter.loadMoreEnd()
                }

            }, {
                toast(it)
                if (!mAdapter.isPageCompleted) {
                    mAdapter.pageError()
                }
            })

        }
    }

    override fun lazyLoad() {
        viewModel.getBannerData()
        viewModel.getContentList(type)
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

    @SuppressLint("NotifyDataSetChanged")
    override fun updateDarkView() {
        binding.constContainer.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorThemeBackground
            )
        )
        binding.toolbar.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorThemeItem
            )
        )
        binding.tvSearch.setBackgroundDrawableCompat(R.drawable.shape_theme_search)
        binding.tvSearch.setTextColorCompat(R.color.colorThemePrimary)
        headerBinding.groupType.setBackgroundColorCompat(R.color.colorThemeItem)

        mAdapter.reInitStatusView()
        mAdapter.notifyDataSetChanged()
    }

}
