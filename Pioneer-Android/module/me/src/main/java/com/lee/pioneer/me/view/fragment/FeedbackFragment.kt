package com.lee.pioneer.me.view.fragment

import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.lee.library.base.BaseVMFragment
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.extensions.toast
import com.lee.library.utils.KeyboardUtil
import com.lee.pioneer.me.databinding.FragmentFeedbackBinding
import com.lee.pioneer.me.viewmodel.FeedbackViewModel
import com.lee.pioneer.me.R

/**
 * @author jv.lee
 * @date 2020/4/22
 * @description MeFragment ChildPage -> 反馈页面
 */
class FeedbackFragment :
    BaseVMNavigationFragment<FragmentFeedbackBinding, FeedbackViewModel>(R.layout.fragment_feedback) {

    override fun bindView() {
        binding.toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorThemeItem))
    }

    override fun bindData() {
        binding.vm = viewModel

        viewModel.toastStrObserver.observe(this, {
            toast(it)
            if (it == getString(R.string.feedback_success)) findNavController().popBackStack()
        })
    }

    override fun onStop() {
        super.onStop()
        KeyboardUtil.hideSoftInput(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
