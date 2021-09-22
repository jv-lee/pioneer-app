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
import com.lee.library.tools.DarkModeTools
import com.lee.library.utils.SPUtil
import com.lee.library.utils.ScreenDensityUtil
import com.lee.library.utils.StatusUtil
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

            StatusUtil.setNavigationBarColor(activity, Color.BLACK)

            if (DarkModeTools.get().isDarkTheme()) {
                StatusUtil.setLightStatusIcon(activity)
            } else {
                StatusUtil.setDarkStatusIcon(activity)
            }
            super.onActivityPreCreated(activity, savedInstanceState)
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
            SPUtil.getInstance(this@App)
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