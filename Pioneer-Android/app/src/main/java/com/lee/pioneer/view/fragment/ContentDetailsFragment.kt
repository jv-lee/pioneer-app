package com.lee.pioneer.view.fragment

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.widget.WebViewEx
import com.lee.pioneer.R
import com.lee.pioneer.constants.HttpConstant
import com.lee.pioneer.databinding.FragmentContentDetailsBinding
import com.lee.pioneer.tools.WebViewTools
import com.lee.library.widget.toolbar.TitleToolbar
import com.lee.pioneer.viewmodel.ContentDetailsViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 内容详情页
 */
class ContentDetailsFragment :
    BaseNavigationFragment<FragmentContentDetailsBinding, ContentDetailsViewModel>(
        R.layout.fragment_content_details,
        ContentDetailsViewModel::class.java
    ) {

    private val detailsUrl by lazy { navArgs<ContentDetailsFragmentArgs>().value.url }
    private val detailsID by lazy { navArgs<ContentDetailsFragmentArgs>().value.id }
    private val web by lazy { WebViewTools.getWeb() }

    override fun bindView() {
        binding.toolbar.setClickListener(object : TitleToolbar.ClickListener() {
            override fun backClick() {
                findNavController().popBackStack()
            }

            override fun menuItemClick(view: View) {
                when (view.id) {
                    R.id.favorite -> viewModel.insertFavorite(detailsID)
                    R.id.share -> {
                    }
                }
            }
        })

        web?.let { it ->
            it.parent?.let { (it as ViewGroup).removeAllViews() }
            binding.frameContainer.addView(it)
            setWebBackEvent(it)
            it.settings.useWideViewPort = true
            it.settings.loadWithOverviewMode = true
            it.addWebStatusListenerAdapter(object : WebViewEx.WebStatusListenerAdapter() {
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
            favoriteObservable.observe(this@ContentDetailsFragment, Observer {
                if (it == 0) {
                    toast("已收藏该内容")
                } else {
                    toast("内容收藏成功")
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        web?.exResume()
    }

    override fun onPause() {
        super.onPause()
        web?.exPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        web?.destroyView()
    }

}
