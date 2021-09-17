package com.lee.pioneer.recommend.view.fragment

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.base.BaseVMFragment
import com.lee.library.extensions.*
import com.lee.library.tools.DarkViewUpdateTools
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.entity.Banner
import com.lee.pioneer.recommend.R
import com.lee.pioneer.recommend.databinding.FragmentRecommendBinding
import com.lee.pioneer.recommend.databinding.LayoutRecommendHeaderBinding
import com.lee.pioneer.recommend.view.adapter.ContentAdapter
import com.lee.pioneer.recommend.view.widget.BannerViewHolder
import com.lee.pioneer.recommend.view.widget.RecommendLoadResource
import com.lee.pioneer.recommend.viewmodel.RecommendViewModel
import com.lee.pioneer.router.navigateDetails
import com.lee.pioneer.router.navigateSearch

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 推荐页面
 */
class RecommendFragment :
    BaseVMFragment<FragmentRecommendBinding, RecommendViewModel>(R.layout.fragment_recommend),
    DarkViewUpdateTools.ViewCallback {

    private var type = "views"

    private lateinit var mAdapter: ContentAdapter

    private val headerBinding by inflate(LayoutRecommendHeaderBinding::inflate)

    @SuppressLint("NotifyDataSetChanged")
    override fun bindView() {
        DarkViewUpdateTools.bindViewCallback(this, this)

        //设置toolbar 搜索跳转
        binding.tvSearch.setOnClickListener {
            findNavController().navigateSearch()
        }

        headerBinding.run {
            //设置推荐头部 banner
            banner.setDelayedTime(5000)
            banner.setBannerPageClickListener { _, position ->
                (banner.data[position] as Banner).let {
                    findNavController().navigateDetails(KeyConstants.CONST_EMPTY, it.url)
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
            adapter = ContentAdapter(requireContext(), arrayListOf()).also {
                mAdapter = it
            }.proxy
        }

        mAdapter.run {
            setLoadResource(RecommendLoadResource())
            initStatusView()
            pageLoading()
            addHeader(headerBinding.root)
            notifyDataSetChanged()
            setOnItemClickListener { _, entity, _ ->
                viewModel.insertContentHistoryToDB(entity)
                findNavController().navigateDetails(entity._id, KeyConstants.CONST_EMPTY)
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
            bannerData.observe(viewLifecycleOwner, {
                headerBinding.banner.setPages(it.toList()) { BannerViewHolder() }
                headerBinding.banner.start()
            }, {
                toast(it)
            })

            contentData.observe(viewLifecycleOwner, {
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
