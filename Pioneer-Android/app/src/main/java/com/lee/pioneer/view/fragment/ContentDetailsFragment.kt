package com.lee.pioneer.view.fragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lee.library.Cache
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.LogUtil
import com.lee.library.widget.WebViewEx
import com.lee.pioneer.R
import com.lee.pioneer.constants.HttpConstant
import com.lee.pioneer.databinding.FragmentContentDetailsBinding
import com.lee.pioneer.tools.WebViewTools
import com.lee.pioneer.view.widget.AppTitleBar
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

    private val detailsID by lazy { navArgs<ContentDetailsFragmentArgs>().value.id }
    private val web by lazy { WebViewTools.getWeb() }

    override fun bindView() {
        LogUtil.i("time - ${System.currentTimeMillis() - Cache.firstTime}")
        binding.toolbar.addClickListener(object : AppTitleBar.ClickListener {
            override fun backClick() {
                findNavController().popBackStack()
            }

            override fun menuClick() {

            }
        })

        binding.frameContainer.addView(web)
        setWebBackEvent(web)
        web.settings.useWideViewPort = true
        web.settings.loadWithOverviewMode = true
        web.addWebStatusListenerAdapter(object : WebViewEx.WebStatusListenerAdapter() {
            override fun callProgress(progress: Int) {
                super.callProgress(progress)
                web.loadUrl(HttpConstant.getNoneHeaderJs())
            }
        })
    }

    override fun bindData() {
        web.loadUrl(HttpConstant.getDetailsUri(detailsID))
        web.exDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.frameContainer.removeAllViews()
    }

}
