package com.lee.pioneer

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.lee.library.base.BaseActivity
import com.lee.pioneer.databinding.ActivityMainBinding
import com.lee.pioneer.view.controller.BottomNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import setupWithNavController

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 程序主窗口
 */
class MainActivity :
    BaseActivity<ActivityMainBinding, ViewModel>(R.layout.activity_main, null),
    BottomNavController {

    private var currentNavController: LiveData<NavController>? = null

    override fun bindData(savedInstanceState: Bundle?) {
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

    override fun hide() {
        binding.bottomNav.visibility = View.GONE
    }

    override fun show() {
        binding.bottomNav.visibility = View.VISIBLE
        binding.navHostContainer.visibility = View.VISIBLE
    }

    /**
     * TODO 启动后主逻辑
     */
    private fun main() {
        launch {
            setupBottomNavigationBar()
            delay(1000)
            initMainUi()
        }
    }

    /**
     * TODO 初始化Fragment导航
     */
    private fun setupBottomNavigationBar() {
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
     * TODO 初始化主界面ui
     */
    private fun initMainUi() {
        window.decorView.background =
            ContextCompat.getDrawable(applicationContext, R.color.colorThemeBackground)
        show()
    }

}
