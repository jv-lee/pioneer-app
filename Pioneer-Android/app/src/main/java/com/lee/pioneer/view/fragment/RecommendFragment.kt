package com.lee.pioneer.view.fragment

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.extensions.glideEnable
import com.lee.library.extensions.setButtonTint
import com.lee.library.utils.LogUtil
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
    BaseNavigationFragment<FragmentRecommendBinding, RecommendViewModel>(R.layout.fragment_recommend) {

    private val headerBinding by lazy {
        DataBindingUtil.inflate<LayoutRecommendHeaderBinding>(
            layoutInflater, R.layout.layout_recommend_header, null, false
        )
    }
    private val mAdapter by lazy { ContentAdapter(requireContext(), ArrayList()) }
    private var type = "views"

    override fun bindView() {
        //设置toolbar 搜索跳转
        binding.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_search)
        }

        headerBinding.run {
            //设置推荐头部 banner
            banner.setDelayedTime(5000)
            banner.setBannerPageClickListener { _, position ->
                (banner.data[position] as Banner).let {
                    findNavController().navigate(
                        MainFragmentDirections.actionMainToContentDetails(
                            KeyConstants.CONST_EMPTY,
                            it.url
                        )
                    )
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
            groupType.setOnCheckedChangeListener { group, checkedId ->
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
            glideEnable()
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
                findNavController().navigate(
                    MainFragmentDirections.actionMainToContentDetails(
                        entity._id,
                        KeyConstants.CONST_EMPTY
                    )
                )
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
            bannerData.observe(viewLifecycleOwner, Observer {
                headerBinding.banner.setPages(it.toList()) { BannerViewHolder() }
                headerBinding.banner.start()
            }, Observer {
                toast(it)
            })

            contentData.observe(viewLifecycleOwner, Observer {
                if (it.data.isNullOrEmpty() && mAdapter.data.isNullOrEmpty()) {
                    mAdapter.pageEmpty()
                } else if (it.data.isNotEmpty()) {
                    mAdapter.updateData(it.data)
                    mAdapter.pageCompleted()
                    mAdapter.loadMoreEnd()
                }

            }, Observer {
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

}
