package com.lee.pioneer.view.fragment

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
    BaseFragment<FragmentFeedbackBinding, FeedbackViewModel>(
        R.layout.fragment_feedback,
        FeedbackViewModel::class.java
    ) {

    override fun bindView() {
    }

    override fun bindData() {
        binding.vm = viewModel

        viewModel.toastStrObserver.observe(this@FeedbackFragment, Observer {
            toast(it)
            if (it == getString(R.string.feedback_success)) findNavController().popBackStack()
        })
    }

}
