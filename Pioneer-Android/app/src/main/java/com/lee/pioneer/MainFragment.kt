package com.lee.pioneer

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.lee.library.adapter.core.UiPager2Adapter
import com.lee.library.base.BaseFragment
import com.lee.library.extensions.binding
import com.lee.library.extensions.delayBackEvent
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.pioneer.databinding.FragmentMainBinding
import com.lee.pioneer.tools.DarkViewUpdateTools
import com.lee.pioneer.view.fragment.GirlFragment
import com.lee.pioneer.view.fragment.HomeFragment
import com.lee.pioneer.view.fragment.MeFragment
import com.lee.pioneer.view.fragment.RecommendFragment

/**
 * @author jv.lee
 * @date 2020.4.17
 * @description RootFragment 是所有Fragment的容器类
 */
class MainFragment : BaseFragment(R.layout.fragment_main),
    DarkViewUpdateTools.ViewCallback {

    private val binding by binding(FragmentMainBinding::bind)

    private lateinit var vpAdapter: UiPager2Adapter

    private val fragments by lazy {
        arrayListOf<Fragment>(
            HomeFragment(),
            RecommendFragment(),
            GirlFragment(),
            MeFragment()
        )
    }

    override fun bindView() {
        //设置回退策略
        delayBackEvent()
        //设置深色主题控制器监听
        DarkViewUpdateTools.bindViewCallback(this, this)

        //初始化view
        binding.vpContainer.adapter = UiPager2Adapter(this, fragments).also {
            vpAdapter = it
        }
        binding.vpContainer.isUserInputEnabled = false
        binding.vpContainer.offscreenPageLimit = vpAdapter.itemCount
        binding.bottomNav.bindViewPager(binding.vpContainer)
    }

    override fun bindData() {

    }

    @SuppressLint("ResourceType")
    override fun updateDarkView() {
        binding.bottomNav.itemTextColor =
            ContextCompat.getColorStateList(requireContext(), R.drawable.main_selector)
        binding.bottomNav.itemIconTintList =
            ContextCompat.getColorStateList(requireContext(), R.drawable.main_selector)
        binding.bottomNav.setBackgroundColorCompat(R.color.colorThemeItem)
    }

}
