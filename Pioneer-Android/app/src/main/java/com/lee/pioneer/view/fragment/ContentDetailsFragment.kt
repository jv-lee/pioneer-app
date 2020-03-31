package com.lee.pioneer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.lee.library.base.BaseNavigationFragment

import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentContentDetailsBinding

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 内容详情页
 */
class ContentDetailsFragment : BaseNavigationFragment<FragmentContentDetailsBinding, ViewModel>(
    R.layout.fragment_content_details,
    null
) {

    private lateinit var detailsID: String

    override fun intentParams(arguments: Bundle?, savedInstanceState: Bundle?) {
        super.intentParams(arguments, savedInstanceState)
        arguments?.let {
            detailsID = arguments.getString("id", "")
        }
    }

    override fun bindView() {
        binding.tvClick.text = detailsID
    }

    override fun bindData() {

    }

}
