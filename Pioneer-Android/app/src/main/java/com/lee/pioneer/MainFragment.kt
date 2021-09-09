package com.lee.pioneer

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lee.library.base.BaseFragment
import com.lee.library.extensions.binding
import com.lee.library.extensions.delayBackEvent
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.pioneer.databinding.FragmentMainBinding
import com.lee.pioneer.library.common.tools.DarkViewUpdateTools

/**
 * @author jv.lee
 * @date 2020.4.17
 * @description RootFragment 是所有Fragment的容器类
 */
class MainFragment : BaseFragment(R.layout.fragment_main),
    DarkViewUpdateTools.ViewCallback {

    private val binding by binding(FragmentMainBinding::bind)

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
            binding.navigationBar.setupWithNavController(controller)
            controller.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.label) {
                    getString(R.string.nav_home),
                    getString(R.string.nav_recommend),
                    getString(R.string.nav_girl),
                    getString(R.string.nav_me) -> {
                        binding.navigationBar.visibility = View.VISIBLE
                    }
                    else -> binding.navigationBar.visibility = View.GONE
                }
            }
        }
    }

}
