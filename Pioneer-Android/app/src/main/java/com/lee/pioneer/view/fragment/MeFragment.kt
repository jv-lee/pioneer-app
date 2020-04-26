package com.lee.pioneer.view.fragment

import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.widget.dialog.ChoiceDialog
import com.lee.library.widget.toolbar.TitleToolbar
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentMeBinding
import com.lee.pioneer.tools.CommonTools
import com.lee.pioneer.tools.PreferencesTools
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

    private val clearDialog by lazy {
        ChoiceDialog.build(context, getString(R.string.me_clear_title)).apply {
            setConfirmListener {
                viewModel.clearCache()
                dismiss()
            }
        }
    }

    override fun bindView() {
        binding.vm = viewModel
        binding.onClickListener = this
        binding.toolbar.setClickListener(object : TitleToolbar.ClickListener() {
            override fun menuClick() {
                binding.isNight = CommonTools.setNightMode()
            }
        })
    }

    override fun bindData() {
        AppCompatDelegate.getDefaultNightMode()
        binding.isNight = PreferencesTools.hasNightMode()

        viewModel.clearObserver.observe(this@MeFragment, Observer {
            toast(if (it) getString(R.string.me_clear_success) else getString(R.string.me_clear_failed))
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.setCacheStr()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.line_message -> findNavController().navigate(R.id.action_main_to_message)
            R.id.line_like -> findNavController().navigate(R.id.action_main_to_like)
            R.id.line_views -> findNavController().navigate(R.id.action_main_to_history)
            R.id.line_favorite -> findNavController().navigate(R.id.action_main_to_collect)
            R.id.line_feedback -> findNavController().navigate(R.id.action_main_to_feedback)
            R.id.line_settings -> clearDialog.show()
        }
    }

}
