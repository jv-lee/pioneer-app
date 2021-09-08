package com.lee.pioneer.details

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.extensions.arguments
import com.lee.library.extensions.setWebBackEvent
import com.lee.library.extensions.toast
import com.lee.library.utils.ShareUtil
import com.lee.library.widget.WebViewEx
import com.lee.library.widget.toolbar.TitleToolbar
import com.lee.pioneer.R
import com.lee.pioneer.constants.HttpConstant
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.details.databinding.FragmentContentDetailsBinding
import com.lee.pioneer.details.viewmodel.ContentDetailsViewModel
import com.lee.pioneer.tools.WebViewTools
import com.lee.pioneer.details.R as DR

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 内容详情页
 */
class ContentDetailsFragment :
    BaseVMNavigationFragment<FragmentContentDetailsBinding, ContentDetailsViewModel>(DR.layout.fragment_content_details) {

    private val detailsUrl by arguments<String>(KeyConstants.KEY_URL)
    private val detailsID by arguments<String>(KeyConstants.KEY_ID)
    private val web by lazy { WebViewTools.getWeb() }

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
            bindLifecycle(requireActivity() as LifecycleOwner)
            parent?.let { (it as ViewGroup).removeAllViews() }
            binding.frameContainer.addView(this)
            setWebBackEvent()
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            addWebStatusListenerAdapter(object : WebViewEx.WebStatusListenerAdapter() {
                override fun callProgress(progress: Int) {
                    binding.progress.visibility = View.VISIBLE
                    binding.progress.progress = progress
                }

                override fun callSuccess() {
                    binding.progress.visibility = View.GONE
                    web?.loadUrl(HttpConstant.getNoneHeaderJs())
                }
            })
        }
    }

    override fun bindData() {
        if (TextUtils.isEmpty(detailsID)) {
            web?.initUrl(detailsUrl)
        } else {
            web?.initUrl(HttpConstant.getDetailsUri(detailsID))
        }

        viewModel.run {
            favoriteObservable.observe(this@ContentDetailsFragment, {
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
