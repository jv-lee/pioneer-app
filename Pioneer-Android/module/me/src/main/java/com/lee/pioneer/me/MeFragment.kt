package com.lee.pioneer.me

import android.Manifest
import android.view.View
import androidx.navigation.fragment.findNavController
import com.imagetools.select.ImageLaunch
import com.imagetools.select.entity.SelectConfig
import com.imagetools.select.entity.TakeConfig
import com.lee.library.base.BaseVMNavigationFragment
import com.lee.library.dialog.ChoiceDialog
import com.lee.library.dialog.core.ConfirmListener
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.library.extensions.setTextColorCompat
import com.lee.library.extensions.show
import com.lee.library.extensions.toast
import com.lee.library.permission.PermissionLauncher
import com.lee.library.utils.CacheUtil
import com.lee.pioneer.R
import com.lee.pioneer.me.databinding.FragmentMeBinding
import com.lee.pioneer.me.viewmodel.MeViewModel
import com.lee.pioneer.library.common.tools.DarkModeTools
import com.lee.pioneer.library.common.tools.DarkViewUpdateTools
import com.lee.pioneer.me.R as MR

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 我的页面
 */
class MeFragment :
    BaseVMNavigationFragment<FragmentMeBinding, MeViewModel>(MR.layout.fragment_me),
    View.OnClickListener, DarkViewUpdateTools.ViewCallback {

    private val imageLaunch = ImageLaunch(this)
    private val permissionLaunch = PermissionLauncher(this)

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
        DarkViewUpdateTools.bindViewCallback(this, this)

        binding.vm = viewModel
        binding.onClickListener = this

        binding.lineMessage.setOnLongClickListener {
            permissionLaunch.requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, {
                imageLaunch.select(
                    SelectConfig(isMultiple = false, isCompress = false, columnCount = 3)
                ) {
                    toast(it[0].uri.toString())
                }
            })
            false
        }
        binding.lineLike.setOnLongClickListener {
            permissionLaunch.requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, {
                imageLaunch.select(SelectConfig(isMultiple = true, isCompress = false)) {
                    toast(it[0].uri.toString())
                }
            })
            false
        }
        binding.lineViews.setOnLongClickListener {
            permissionLaunch.requestPermission(Manifest.permission.CAMERA, {
                imageLaunch.take(TakeConfig(isCrop = true)) {
                    toast(it.uri.toString())
                }
            })
            false
        }
        binding.lineFavorite.setOnLongClickListener {
            findNavController().navigate(MR.id.action_me_to_size)
            false
        }
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
                DarkViewUpdateTools.notifyUiMode()
            }
        }
        binding.switchDarkEnable.setOnCheckedChangeListener { _, isChecked ->
            if (isResumed) {
                DarkModeTools.get().updateNightTheme(isChecked)
                DarkViewUpdateTools.notifyUiMode()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            MR.id.line_message -> findNavController().navigate(MR.id.action_me_to_message)
            MR.id.line_like -> findNavController().navigate(MR.id.action_me_to_like)
            MR.id.line_views -> findNavController().navigate(MR.id.action_me_to_history)
            MR.id.line_favorite -> findNavController().navigate(MR.id.action_me_to_collect)
            MR.id.line_feedback -> findNavController().navigate(MR.id.action_me_to_feedback)
            MR.id.line_settings -> show(clearDialog)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.switchDarkEnable.setOnCheckedChangeListener(null)
        binding.switchSystemEnable.setOnCheckedChangeListener(null)
    }

    override fun updateDarkView() {
        binding.toolbar.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.toolbar.setTitleColor(R.color.colorAccent)
        binding.constContainer.setBackgroundColorCompat(R.color.colorThemeBackground)

        binding.lineMessage.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.lineMessage.getLeftTextView()?.setTextColorCompat(R.color.colorThemeAccent)

        binding.lineFavorite.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.lineFavorite.getLeftTextView()?.setTextColorCompat(R.color.colorThemeAccent)

        binding.lineFeedback.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.lineFeedback.getLeftTextView()?.setTextColorCompat(R.color.colorThemeAccent)

        binding.lineLike.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.lineLike.getLeftTextView()?.setTextColorCompat(R.color.colorThemeAccent)

        binding.lineSettings.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.lineSettings.getLeftTextView()?.setTextColorCompat(R.color.colorThemeAccent)

        binding.lineViews.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.lineViews.getLeftTextView()?.setTextColorCompat(R.color.colorThemeAccent)

        binding.frameSystem.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.tvSystem.setTextColorCompat(R.color.colorThemeAccent)

        binding.frameNight.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.tvNight.setTextColorCompat(R.color.colorThemeAccent)
    }

}
