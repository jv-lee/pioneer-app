package com.lee.pioneer.view.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.KeyboardUtil
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentSearchBinding

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : BaseNavigationFragment<FragmentSearchBinding,ViewModel>(R.layout.fragment_search,null) {
    override fun bindView() {
        binding.tvCancel.setOnClickListener {
            KeyboardUtil.hideSoftInput(activity)
            findNavController().popBackStack() }
    }

    override fun bindData() {

    }


}
