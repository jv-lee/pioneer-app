package com.lee.pioneer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import com.lee.library.base.BaseActivity
import com.lee.library.extensions.banBackEvent
import com.lee.library.extensions.binding
import com.lee.library.utils.DensityUtil
import com.lee.library.utils.StatusUtil
import com.lee.pioneer.databinding.ActivityMainBinding
import com.lee.pioneer.tools.DarkModeTools
import com.lee.pioneer.tools.WebViewTools
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020.3.27
 * @description 程序主窗口 单Activity架构
 */
class MainActivity : BaseActivity() {

    private val binding by binding(ActivityMainBinding::inflate)

    private val backCallback = banBackEvent()

    override fun initSavedState(intent: Intent, savedInstanceState: Bundle?) {
        super.initSavedState(intent, savedInstanceState)
        if (savedInstanceState == null) {
            launch {
                //进程初始化启动 请求配置
                requestConfig()
                animVisibleUi(300)
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        launch {
            //程序以外重启 或重新创建MainActivity 无需获取配置，直接显示view
            animVisibleUi(0)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (DarkModeTools.get().isDarkTheme()) {
            StatusUtil.setLightStatusIcon(this)
        } else {
            StatusUtil.setDarkStatusIcon(this)
        }
    }

    override fun bindView() {
        //初始化全局webView
        WebViewTools.get(applicationContext)
    }

    override fun bindData() {
    }

    @ExperimentalCoroutinesApi
    override fun onDestroy() {
        cancel()
        WebViewTools.get().onDestroy()
        super.onDestroy()
    }

    //客户端入口读取APP配置
    private suspend fun requestConfig() {
        delay(500)
    }

    /**
     * 动画显示UI页面
     * @param duration 页面预加载时间
     */
    private suspend fun animVisibleUi(duration: Long) {
        //移除back事件禁用
        backCallback.remove()
        //预加载预留时间
        delay(duration)
        //设置动画显示rootView
        val anim = ObjectAnimator.ofFloat(0F, 1F)
        anim.duration = duration
        anim.interpolator = LinearInterpolator()
        anim.addUpdateListener {
            binding.mainContainer.alpha = it.animatedValue as Float
        }
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                binding.mainContainer.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator?) {
                window.decorView.background = null
            }
        })
        anim.start()
    }

}
