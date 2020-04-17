package com.lee.pioneer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.res.Resources
import android.view.View
import android.view.animation.LinearInterpolator
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
            animUi()
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

    private fun animUi() {
        val anim = ObjectAnimator.ofFloat(0F, 1F)
        anim.duration = 300
        anim.interpolator = LinearInterpolator()
        anim.addUpdateListener {
            binding.mainContainer.alpha = it.getAnimatedValue() as Float
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
