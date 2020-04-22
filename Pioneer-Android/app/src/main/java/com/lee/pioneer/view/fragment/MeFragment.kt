package com.lee.pioneer.view.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.widget.toolbar.TitleToolbar
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentMeBinding
import com.lee.pioneer.viewmodel.MeViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 我的页面
 */
class MeFragment :
    BaseNavigationFragment<FragmentMeBinding, MeViewModel>(
        R.layout.fragment_me,
        MeViewModel::class.java
    ), View.OnClickListener {

    override fun bindView() {
        binding.onClickListener = this
        binding.toolbar.setClickListener(object : TitleToolbar.ClickListener() {
            override fun menuClick() {
                binding.toolbar.ivMenu?.setImageResource(R.drawable.vector_theme_mode_night)
            }
        })
    }

    override fun bindData() {
        binding.isNight = false
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.line_message -> toast("message 功能暂未开发")
            R.id.line_like -> toast("like 功能暂未开发")
            R.id.line_views -> findNavController().navigate(R.id.action_main_to_history)
            R.id.line_favorite -> findNavController().navigate(R.id.action_main_to_collect)
            R.id.line_feedback -> findNavController().navigate(R.id.action_main_to_feedback)
            R.id.line_settings -> findNavController().navigate(R.id.action_main_to_settings)
        }
    }

}
