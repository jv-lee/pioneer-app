package com.lee.pioneer.view.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.widget.WebViewEx
import com.lee.pioneer.R
import com.lee.pioneer.constants.HttpConstant
import com.lee.pioneer.databinding.FragmentContentDetailsBinding
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
//        binding.ivBack.setOnClickListener { findNavController().popBackStack() }

        setWebBackEvent(binding.web)
        binding.web.visibility = View.GONE
        binding.web.settings.useWideViewPort = true
        binding.web.settings.loadWithOverviewMode = true
        binding.web.addWebStatusListenerAdapter(object : WebViewEx.WebStatusListenerAdapter() {
            override fun callSuccess() {
                binding.web.loadUrl(HttpConstant.getNoneHeaderJs())
                binding.web.visibility = View.VISIBLE
                super.callSuccess()
            }
        })
    }

    override fun bindData() {
        toast(detailsID)
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
