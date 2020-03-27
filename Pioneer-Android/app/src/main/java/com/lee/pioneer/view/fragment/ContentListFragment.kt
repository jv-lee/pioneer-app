package com.lee.pioneer.view.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.LaunchActivity
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentContentListBinding

/**
 * A simple [Fragment] subclass.
 */
class ContentListFragment :
    BaseNavigationFragment<FragmentContentListBinding, ViewModel>(R.layout.fragment_content_list, null) {

    override fun bindView() {
        binding.text.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_contentDetailsFragment)
            (activity as LaunchActivity).hideView()
        }
    }

    override fun bindData() {

    }

}
