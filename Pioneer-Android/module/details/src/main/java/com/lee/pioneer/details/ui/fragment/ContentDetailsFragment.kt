package com.lee.pioneer.details.ui.fragment

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.postDelayed
import androidx.lifecycle.LifecycleOwner
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.extensions.arguments
import com.lee.library.extensions.setWebBackEvent
import com.lee.library.extensions.toast
import com.lee.library.tools.WebViewTools
import com.lee.library.utils.ShareUtil
import com.lee.library.widget.AppWebView
import com.lee.library.widget.skeleton.Skeleton
import com.lee.library.widget.toolbar.TitleToolbar
import com.lee.pioneer.details.R
import com.lee.pioneer.details.databinding.FragmentContentDetailsBinding
import com.lee.pioneer.details.viewmodel.ContentDetailsViewModel
import com.lee.pioneer.library.common.constant.HttpConstant
import com.lee.pioneer.library.common.constant.KeyConstants

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 内容详情页
 */
class ContentDetailsFragment :
    BaseVMNavigationFragment<FragmentContentDetailsBinding, ContentDetailsViewModel>(R.layout.fragment_content_details) {

    private val detailsUrl by arguments<String>(KeyConstants.KEY_URL)
    private val detailsID by arguments<String>(KeyConstants.KEY_ID)
    private val web by lazy { WebViewTools.getWeb(requireActivity().applicationContext) }
    private lateinit var skeleton: Skeleton

    override fun bindView() {
        binding.toolbar.setClickListener(object : TitleToolbar.ClickListener() {

            override fun menuClick() {
                binding.toolbar.showMenu(-40, 10)
            }

            override fun menuItemClick(view: View) {
                when (view.id) {
                    R.id.collect -> {
                        viewModel.contentCollect(detailsID)
                    }
                    R.id.share -> {
                        ShareUtil.shareText(
                            context,
                            if (TextUtils.isEmpty(detailsUrl)) HttpConstant.getDetailsUri(detailsID) else detailsUrl
                        )
                    }
                }
            }
        })
        web?.run {
            setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorThemeBackground))
            bindLifecycle(requireActivity() as LifecycleOwner)
            parent?.let { (it as ViewGroup).removeAllViews() }
            binding.frameContainer.addView(this)
            setWebBackEvent()
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true

            //添加webView骨架viewLoading
            skeleton = Skeleton.Builder(binding.frameContainer)
                .load(R.layout.layout_content_skeleton)
                .bind()

            addWebStatusListenerAdapter(object : AppWebView.WebStatusListenerAdapter() {
                override fun callStart() {
                    web?.visibility = View.INVISIBLE
                }

                override fun callProgress(progress: Int) {
                    binding.progress.visibility = View.VISIBLE
                    binding.progress.progress = progress
                }

                override fun callSuccess() {
                    web?.loadUrl(HttpConstant.getNoneHeaderJs())
                    binding.progress.visibility = View.GONE

                    web?.postDelayed(100){
                        web?.visibility = View.VISIBLE
                        skeleton.unBind()
                    }
                }

                override fun callFailed() {
                    web?.postDelayed(100){
                        web?.visibility = View.VISIBLE
                        skeleton.unBind()
                    }
                }
            })
        }
    }

    override fun bindData() {
        if (detailsID == KeyConstants.CONST_EMPTY) {
            web?.initUrl(detailsUrl)
        } else {
            web?.initUrl(HttpConstant.getDetailsUri(detailsID))
        }

        viewModel.run {
            favoriteLive.observe(this@ContentDetailsFragment, {
                if (it == 0) {
                    toast(getString(R.string.menu_collect_completed))
                } else {
                    toast(getString(R.string.menu_collect_complete))
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        web?.destroyView()
    }

}
