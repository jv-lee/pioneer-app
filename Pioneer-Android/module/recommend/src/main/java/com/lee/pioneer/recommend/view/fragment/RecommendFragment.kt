package com.lee.pioneer.recommend.view.fragment

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.adapter.base.BaseViewAdapter
import com.lee.library.adapter.listener.LoadErrorListener
import com.lee.library.adapter.page.submitFailed
import com.lee.library.adapter.page.submitSinglePage
import com.lee.library.base.BaseVMFragment
import com.lee.library.extensions.*
import com.lee.library.mvvm.ui.collectState
import com.lee.library.mvvm.ui.observeState
import com.lee.library.tools.DarkViewUpdateTools
import com.lee.library.widget.StatusLayout
import com.lee.library.widget.banner.holder.ImageCreateHolder
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.entity.Banner
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.entity.PageData
import com.lee.pioneer.library.common.tools.GlideTools
import com.lee.pioneer.recommend.R
import com.lee.pioneer.recommend.databinding.FragmentRecommendBinding
import com.lee.pioneer.recommend.databinding.LayoutRecommendHeaderBinding
import com.lee.pioneer.recommend.view.adapter.ContentAdapter
import com.lee.pioneer.recommend.view.widget.RecommendLoadResource
import com.lee.pioneer.recommend.viewmodel.RecommendViewModel
import com.lee.pioneer.router.navigateDetails
import com.lee.pioneer.router.navigateSearch
import kotlinx.coroutines.flow.collect

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 推荐页面
 */
class RecommendFragment :
    BaseVMFragment<FragmentRecommendBinding, RecommendViewModel>(R.layout.fragment_recommend),
    DarkViewUpdateTools.ViewCallback {

    companion object {
        const val TYPE_VIEWS = "views"
        const val TYPE_LIKES = "likes"
        const val TYPE_COMMENTS = "comments"

        private fun getCheckType(checkedId: Int): String {
            return when (checkedId) {
                R.id.radio_view -> TYPE_VIEWS
                R.id.radio_like -> TYPE_LIKES
                R.id.radio_comment -> TYPE_COMMENTS
                else -> TYPE_VIEWS
            }
        }
    }

    private lateinit var mAdapter: BaseViewAdapter<Content>

    private val headerBinding by inflate(LayoutRecommendHeaderBinding::inflate)

    @SuppressLint("NotifyDataSetChanged")
    override fun bindView() {
        DarkViewUpdateTools.bindViewCallback(this, this)

        //设置toolbar 搜索跳转
        binding.tvSearch.setOnClickListener {
            findNavController().navigateSearch()
        }

        headerBinding.bannerStatusLayout.setOnReloadListener {
            viewModel.requestBanner()
        }

        headerBinding.run {
            //设置推荐头部 分类样式
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
                viewModel.switchType(getCheckType(checkedId))
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
            addHeader(headerBinding.root)
            notifyDataSetChanged()
            setOnItemClickListener { _, entity, _ ->
                viewModel.insertContentHistoryToDB(entity)
                findNavController().navigateDetails(entity._id, KeyConstants.CONST_EMPTY)
            }
            setLoadErrorListener(object : LoadErrorListener {
                override fun itemReload() {}

                override fun pageReload() {
                    viewModel.switchType(getCheckType(headerBinding.groupType.checkedRadioButtonId))
                }

            })
        }
    }

    override fun bindData() {
        viewModel.bannerLive.observeState<List<Banner>>(viewLifecycleOwner, success = {
            headerBinding.bannerStatusLayout.setStatus(StatusLayout.STATUS_DATA)
            headerBinding.banner.bindDataCreate(it, object : ImageCreateHolder<Banner>() {
                override fun bindItem(imageView: ImageView, data: Banner) {
                    GlideTools.get().loadImage(data.image, imageView)
                }

                override fun onItemClick(position: Int, item: Banner) {
                    findNavController().navigateDetails(KeyConstants.CONST_EMPTY, item.url)
                }

            })
        }, error = {
            toast(it.message)
            headerBinding.bannerStatusLayout.setStatus(StatusLayout.STATUS_DATA_ERROR)
        }, loading = {
            headerBinding.bannerStatusLayout.setStatus(StatusLayout.STATUS_LOADING)
        })

        launchAndRepeatWithViewLifecycle {
            viewModel.typeAction.collect { typeAction ->
                when (typeAction.value) {
                    TYPE_VIEWS -> headerBinding.groupType.checkUnNotification(R.id.radio_view)
                    TYPE_LIKES -> headerBinding.groupType.checkUnNotification(R.id.radio_like)
                    TYPE_COMMENTS -> headerBinding.groupType.checkUnNotification(R.id.radio_comment)
                }
            }
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.contentFlow.collectState<PageData<Content>>(success = {
                mAdapter.submitSinglePage(it.data)
            }, error = {
                toast(it.message)
                mAdapter.submitFailed()
            }, loading = {
                mAdapter.initStatusView()
                mAdapter.pageLoading()
            })
        }
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
