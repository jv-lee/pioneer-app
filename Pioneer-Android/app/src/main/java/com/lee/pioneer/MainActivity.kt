package com.lee.pioneer

import android.content.res.Resources
import android.view.View
import androidx.lifecycle.ViewModel
import com.lee.library.base.BaseActivity
import com.lee.library.utils.AdaptScreenUtils
import com.lee.pioneer.databinding.ActivityMainBinding
import com.lee.pioneer.tools.WebViewTools
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020.3.27
 * @description 程序主窗口 单Activity架构
 */
class MainActivity :
    BaseActivity<ActivityMainBinding, ViewModel>(R.layout.activity_main, null) {

    override fun bindView() {
        launch {
            delay(1000)
            window.decorView.background = null
            binding.mainContainer.visibility = View.VISIBLE
        }
    }

    override fun bindData() {
        //初始化全局webView
        WebViewTools.get(applicationContext)
    }

    /**
     * 设置屏幕适配
     */
    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 360)
    }

}
