package com.lee.pioneer.view.fragment

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lee.library.base.BaseFragment
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentFeedbackBinding
import com.lee.pioneer.viewmodel.FeedbackViewModel

/**
 * @author jv.lee
 * @date 2020/4/22
 * @description MeFragment ChildPage -> 反馈页面
 */
class FeedbackFragment :
    BaseFragment<FragmentFeedbackBinding, FeedbackViewModel>(R.layout.fragment_feedback) {

    override fun bindView() {
        binding.toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorThemeItem))
    }

    override fun bindData() {
        binding.vm = viewModel

        viewModel.toastStrObserver.observe(viewLifecycleOwner, Observer {
            toast(it)
            if (it == getString(R.string.feedback_success)) findNavController().popBackStack()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
