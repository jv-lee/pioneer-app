package com.lee.pioneer.view.fragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.widget.WebViewEx
import com.lee.pioneer.R
import com.lee.pioneer.constants.HttpConstant
import com.lee.pioneer.databinding.FragmentContentDetailsBinding
import com.lee.pioneer.view.widget.AppTitleBar
import com.lee.pioneer.viewmodel.ContentDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

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

    override fun bindView() {
        binding.toolbar.addClickListener(object : AppTitleBar.ClickListener {
            override fun backClick() {
                findNavController().popBackStack()
            }

            override fun menuClick() {

            }
        })

        setWebBackEvent(binding.web)
        binding.web.settings.useWideViewPort = true
        binding.web.settings.loadWithOverviewMode = true
        binding.web.addWebStatusListenerAdapter(object : WebViewEx.WebStatusListenerAdapter() {
            override fun callProgress(progress: Int) {
                super.callProgress(progress)
                binding.web.loadUrl(HttpConstant.getNoneHeaderJs())
            }
        })
    }

    override fun bindData() {
        binding.web.loadUrl(HttpConstant.getDetailsUri(detailsID))
    }

    override fun onResume() {
        super.onResume()
        binding.web.exResume()
    }

    override fun onPause() {
        super.onPause()
        binding.web.exPause()
    }

    @ExperimentalCoroutinesApi
    override fun onDetach() {
        super.onDetach()
        binding.web.exDestroy()
    }

}
