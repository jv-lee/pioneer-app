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

    override fun bindView() {
        binding.tvClick.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun bindData() {

    }

}
