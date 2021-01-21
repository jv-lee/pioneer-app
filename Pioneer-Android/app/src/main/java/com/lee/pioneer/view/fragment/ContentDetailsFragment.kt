package com.lee.pioneer.view.fragment

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.ShareUtil
import com.lee.library.widget.WebViewEx
import com.lee.library.widget.toolbar.TitleToolbar
import com.lee.pioneer.R
import com.lee.pioneer.constants.HttpConstant
import com.lee.pioneer.databinding.FragmentContentDetailsBinding
import com.lee.pioneer.tools.WebViewTools
import com.lee.pioneer.viewmodel.ContentDetailsViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 内容详情页
 */
class ContentDetailsFragment :
    BaseNavigationFragment<FragmentContentDetailsBinding, ContentDetailsViewModel>(R.layout.fragment_content_details) {

    private val detailsUrl by lazy { navArgs<ContentDetailsFragmentArgs>().value.url }
    private val detailsID by lazy { navArgs<ContentDetailsFragmentArgs>().value.id }
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
            bindLifecycle(requireActivity())
            parent?.let { (it as ViewGroup).removeAllViews() }
            binding.frameContainer.addView(this)
            setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorThemeBackground
                )
            )
            setWebBackEvent(this)
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
            favoriteObservable.observe(viewLifecycleOwner, Observer {
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
