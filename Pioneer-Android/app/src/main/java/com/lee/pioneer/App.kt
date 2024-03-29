package com.lee.pioneer

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lee.library.base.BaseApplication
import com.lee.library.cache.CacheManager
import com.lee.library.extensions.bindFragmentLifecycle
import com.lee.library.lifecycle.SimpleActivityLifecycleCallbacks
import com.lee.library.lifecycle.SimpleFragmentLifecycleCallbacks
import com.lee.library.net.HttpManager
import com.lee.library.tools.DarkModeTools
import com.lee.library.tools.ScreenDensityUtil
import com.lee.library.tools.StatusTools
import com.lee.pioneer.library.service.hepler.ApplicationModuleService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020/3/23
 * @description 程序入口
 */
class App : BaseApplication() {

    private val fragmentLifecycleCallbacks = object : SimpleFragmentLifecycleCallbacks() {

        override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
            ScreenDensityUtil.init(f.requireActivity())
            super.onFragmentPreAttached(fm, f, context)
        }

    }

    private val activityLifecycleCallbacks = object : SimpleActivityLifecycleCallbacks() {

        override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
            ScreenDensityUtil.init(activity)

            activity.bindFragmentLifecycle(fragmentLifecycleCallbacks)

            StatusTools.setNavigationBarColor(activity, Color.BLACK)

            if (DarkModeTools.get().isDarkTheme()) {
                StatusTools.setLightStatusIcon(activity)
            } else {
                StatusTools.setDarkStatusIcon(activity)
            }
            super.onActivityPreCreated(activity, savedInstanceState)
        }

        override fun onActivityDestroyed(activity: Activity) {
            super.onActivityDestroyed(activity)
            ScreenDensityUtil.resetDensity(activity)
        }

    }

    override fun init() {
        ScreenDensityUtil.init(this)
        //深色主题适配
        if (!DarkModeTools.get(applicationContext).isSystemTheme()) {
            DarkModeTools.get().updateNightTheme(DarkModeTools.get().isDarkTheme())
        }

        //初始化工具类
        CoroutineScope(Dispatchers.IO).launch {
            HttpManager.getInstance().setUnSafeClient(true)
            CacheManager.init(this@App, BuildConfig.VERSION_CODE)
            ApplicationModuleService.init(this@App)
        }

        //注册Activity生命周期监听
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)

        //初始化内存检测工具
//        if (!LeakCanary.isInAnalyzerProcess(this)) {
//            LeakCanary.install(this)
//        }
    }

    override fun unInit() {
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

}