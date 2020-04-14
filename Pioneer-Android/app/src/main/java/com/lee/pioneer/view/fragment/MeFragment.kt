package com.lee.pioneer.view.fragment

import android.view.View
import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentMeBinding
import com.lee.pioneer.view.widget.toolbar.TitleToolbar
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
        binding.toolbar.addClickListener(object : TitleToolbar.ClickListener() {
            override fun menuClick() {
                binding.toolbar.ivMenu?.setImageResource(R.drawable.vector_theme_mode_night)
            }
        })
    }

    override fun bindData() {
        binding.isNight = false
    }

    override fun onResume() {
        super.onResume()
        showNavigation()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.line_message -> toast("message")
            R.id.line_like -> toast("like")
            R.id.line_views -> toast("views")
            R.id.line_favorite -> toast("favorite")
            R.id.line_feedback -> toast("feedback")
            R.id.line_settings -> toast("settings")
        }
    }

}
