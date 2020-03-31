package com.lee.pioneer.view.fragment

import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.lifecycle.Observer
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.widget.WebViewEx
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentContentDetailsBinding
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

    private var detailsID: String? = null

    override fun intentParams(arguments: Bundle?, savedInstanceState: Bundle?) {
        super.intentParams(arguments, savedInstanceState)
        arguments?.let {
            detailsID = arguments.getString("id", "")
        }
    }

    override fun bindView() {
        setWebBackEvent(binding.web)
        binding.web.visibility = View.GONE
        binding.web.settings.useWideViewPort = true
        binding.web.settings.loadWithOverviewMode = true
        binding.web.addWebStatusListenerAdapter(object:WebViewEx.WebStatusListenerAdapter(){
            override fun callSuccess() {
                val js = "javascript:(function(){document.getElementsByClassName('header')[0].style.display = 'none'})()"
                binding.web.loadUrl(js)
                binding.web.visibility = View.VISIBLE
                super.callSuccess()
            }
        })
    }

    override fun bindData() {
        detailsID?.let {
            binding.web.loadUrl("https://gank.io/post/$it")
        }
    }

    override fun onResume() {
        super.onResume()
        binding.web.exResume()
    }

    override fun onPause() {
        super.onPause()
        binding.web.exPause()
    }

    override fun onDetach() {
        super.onDetach()
        binding.web.exDestroy()
    }

}
