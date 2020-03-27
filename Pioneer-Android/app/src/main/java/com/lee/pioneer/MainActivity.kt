package com.lee.pioneer

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.lee.library.adapter.UiPagerAdapter
import com.lee.library.base.BaseActivity
import com.lee.pioneer.databinding.ActivityMainBinding
import com.lee.pioneer.view.controller.BottomNavController
import com.lee.pioneer.view.fragment.FavoriteFragment
import com.lee.pioneer.view.fragment.HomeFragment
import com.lee.pioneer.view.fragment.MeFragment
import com.lee.pioneer.view.fragment.RecommendFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020.3.27
 * @description 程序主窗口 使用ViewPager去控制多fragment分页
 */
@Deprecated("使用单Activity架构LaunchActivity")
class MainActivity :
    BaseActivity<ActivityMainBinding, ViewModel>(R.layout.activity_main, null),
    BottomNavController {

    private val vpAdapter by lazy { UiPagerAdapter(supportFragmentManager, fragments, titles) }
    private val fragments by lazy {
        listOf<Fragment>(
            HomeFragment(),
            RecommendFragment(),
            FavoriteFragment(),
            MeFragment()
        )
    }
    private val titles by lazy {
        listOf(
            getString(R.string.nav_home),
            getString(R.string.nav_recommend),
            getString(R.string.nav_favorite),
            getString(R.string.nav_me)
        )
    }

    override fun bindView() {
        binding.pagerContainer.adapter = vpAdapter
        binding.pagerContainer.offscreenPageLimit = fragments.size - 1
        binding.pagerContainer.setNoScroll(true)
        binding.bottomNav.bindViewPager(binding.pagerContainer)
    }

    override fun bindData() {
        main()
    }

    override fun hide() {
        binding.bottomNav.visibility = View.GONE
    }

    override fun show() {
        binding.bottomNav.visibility = View.VISIBLE
        binding.pagerContainer.visibility = View.VISIBLE
    }

    /**
     * TODO 启动后主逻辑
     */
    private fun main() {
        launch {
            delay(1000)
            initMainUi()
        }
    }

    /**
     * TODO 初始化主界面ui
     */
    private fun initMainUi() {
        window.decorView.background =
            ContextCompat.getDrawable(applicationContext, R.color.colorThemeBackground)
        show()
    }
}
