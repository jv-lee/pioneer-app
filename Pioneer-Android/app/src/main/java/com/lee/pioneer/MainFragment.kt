package com.lee.pioneer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.lee.library.base.BaseFragment
import com.lee.library.extensions.binding
import com.lee.library.extensions.delayBackEvent
import com.lee.library.extensions.endListener
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.library.tools.DarkViewUpdateTools
import com.lee.pioneer.databinding.FragmentMainBinding
import com.lee.pioneer.library.common.tools.setupWithNavController2
import java.lang.ref.WeakReference

/**
 * @author jv.lee
 * @date 2020.4.17
 * @description RootFragment 是所有Fragment的容器类
 */
class MainFragment : BaseFragment(R.layout.fragment_main),
    DarkViewUpdateTools.ViewCallback {

    private val binding by binding(FragmentMainBinding::bind)

    private val navigationInAnim by lazy {
        AnimationUtils.loadAnimation(requireContext(), R.anim.slide_bottom_in).apply {
            endListener {
                binding.navigationBar.visibility = View.VISIBLE
                binding.navigationBar.clearAnimation()
            }
        }
    }

    private val navigationOutAnim by lazy {
        AnimationUtils.loadAnimation(requireContext(), R.anim.slide_bottom_out).apply {
            endListener {
                binding.navigationBar.visibility = View.GONE
                binding.navigationBar.clearAnimation()
            }
        }
    }

    override fun bindView() {
        //设置回退策略
        delayBackEvent()

        //设置深色主题控制器监听
        DarkViewUpdateTools.bindViewCallback(this, this)

        //fragment容器与navigationBar绑定
        bindNavigationAction()
    }

    override fun bindData() {

    }

    @SuppressLint("ResourceType")
    override fun updateDarkView() {
        binding.navigationBar.itemTextColor =
            ContextCompat.getColorStateList(requireContext(), R.drawable.main_selector)
        binding.navigationBar.itemIconTintList =
            ContextCompat.getColorStateList(requireContext(), R.drawable.main_selector)
        binding.navigationBar.setBackgroundColorCompat(R.color.colorThemeItem)
    }

    private fun bindNavigationAction() {
        binding.navigationBar.post {
            val controller = binding.container.findNavController()
            binding.navigationBar.setupWithNavController2(controller)

            val weakReference = WeakReference(binding.navigationBar)
            controller.addOnDestinationChangedListener(object :
                NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    val view = weakReference.get()
                    if (view == null) {
                        controller.removeOnDestinationChangedListener(this)
                        return
                    }
                    when (destination.label) {
                        getString(R.string.module_home),
                        getString(R.string.module_recommend),
                        getString(R.string.module_girl),
                        getString(R.string.module_me) -> {
                            if (view.visibility == View.GONE && view.animation == null) {
                                view.startAnimation(navigationInAnim)
                            }
                        }
                        else -> {
                            if (view.visibility == View.VISIBLE && view.animation == null) {
                                view.startAnimation(navigationOutAnim)
                            }
                        }
                    }
                }
            })
        }
    }

}
