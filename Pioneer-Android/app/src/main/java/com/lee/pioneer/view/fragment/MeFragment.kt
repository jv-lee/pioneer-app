package com.lee.pioneer.view.fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.dialog.ChoiceDialog
import com.lee.library.dialog.core.ConfirmListener
import com.lee.library.utils.CacheUtil
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentMeBinding
import com.lee.pioneer.tools.DarkModeTools
import com.lee.pioneer.viewmodel.MeViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 我的页面
 */
class MeFragment :
    BaseNavigationFragment<FragmentMeBinding, MeViewModel>(R.layout.fragment_me),
    View.OnClickListener {

    private val clearDialog by lazy {
        ChoiceDialog(requireContext()).apply {
            setTitle(getString(R.string.me_clear_title))
            setCancelable(true)
            confirmListener = ConfirmListener {
                if (CacheUtil.clearAllCache(activity)) {
                    viewModel.totalCacheStr.set(CacheUtil.getTotalCacheSize(activity))
                    toast(getString(R.string.me_clear_success))
                } else {
                    toast(getString(R.string.me_clear_failed))
                }
                dismiss()
            }
        }
    }

    override fun bindView() {
        binding.vm = viewModel
        binding.onClickListener = this
    }

    override fun bindData() {
        binding.isSystem = DarkModeTools.get().isSystemTheme()
        binding.isNight = DarkModeTools.get().isDarkTheme()
    }

    override fun onResume() {
        super.onResume()
        viewModel.totalCacheStr.set(CacheUtil.getTotalCacheSize(activity))
        binding.switchSystemEnable.setOnCheckedChangeListener { _, isChecked ->
            if (isResumed) {
                binding.isSystem = isChecked
                DarkModeTools.get().updateSystemTheme(isChecked)
            }
        }
        binding.switchDarkEnable.setOnCheckedChangeListener { _, isChecked ->
            if (isResumed) {
                DarkModeTools.get().updateNightTheme(isChecked)
            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.switchDarkEnable.setOnCheckedChangeListener(null)
        binding.switchSystemEnable.setOnCheckedChangeListener(null)
    }

}
