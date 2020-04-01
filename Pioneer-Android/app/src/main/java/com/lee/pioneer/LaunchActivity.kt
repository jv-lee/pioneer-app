package com.lee.pioneer

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.lee.library.base.BaseNavigationActivity
import com.lee.library.setupWithNavController
import com.lee.library.utils.DensityUtil
import com.lee.pioneer.databinding.ActivityLaunchBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 程序主窗口 使用FragmentContainerView去控制多fragment分页 单Activity架构 使用navigation框架
 */
class LaunchActivity :
    BaseNavigationActivity<ActivityLaunchBinding, ViewModel>(R.layout.activity_launch, null) {

    private var currentNavController: LiveData<NavController>? = null

    override fun intentParams(intent: Intent, savedInstanceState: Bundle?) {
        super.intentParams(intent, savedInstanceState)
        savedInstanceState ?: main()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        main()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun bindView() {

    }

    override fun bindData() {
    }

    /**
     * TODO 启动后主逻辑
     */
    private fun main() {
        launch {
            setupBottomNavigationBar()
            delay(2000)
            initMainUi()
        }
    }

    /**
     * TODO 初始化Fragment导航
     */
    private fun setupBottomNavigationBar() {
        withBottomNavigationView(binding.bottomNav)
        withFragmentContainerView(binding.navHostContainer)

        val navGraphIds =
            listOf(
                R.navigation.home,
                R.navigation.recommend,
                R.navigation.favorite,
                R.navigation.me
            )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = binding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )
        currentNavController = controller
    }

    /**
     * TODO 显示主界面
     */
    private fun initMainUi() {
        window.decorView.background =
            ContextCompat.getDrawable(applicationContext, R.color.colorThemeBackground)
        setNavigationVisible(true)
        showView()
    }

}
