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
import com.lee.library.extensions.unbindFragmentLifecycle
import com.lee.library.lifecycle.SimpleActivityLifecycleCallbacks
import com.lee.library.lifecycle.SimpleFragmentLifecycleCallbacks
import com.lee.library.utils.DensityUtil
import com.lee.library.utils.SPUtil
import com.lee.library.utils.StatusUtil
import com.lee.pioneer.db.AppDataBase
import com.lee.pioneer.tools.DarkModeTools
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020/3/23
 * @description 程序入口
 */
class App : BaseApplication() {

    private val fragmentLifecycleCallbacks = object : SimpleFragmentLifecycleCallbacks() {

        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            DensityUtil.setDensity(f.requireActivity())
            super.onFragmentAttached(fm, f, context)
        }
    }

    private val activityLifecycleCallbacks = object : SimpleActivityLifecycleCallbacks() {

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            DensityUtil.setDensity(activity)

            activity.bindFragmentLifecycle(fragmentLifecycleCallbacks)

            StatusUtil.setNavigationBarColor(activity, Color.BLACK)
            if (DarkModeTools.get().isDarkTheme()) {
                StatusUtil.setLightStatusIcon(activity)
            } else {
                StatusUtil.setDarkStatusIcon(activity)
            }
            super.onActivityCreated(activity, bundle)
        }

        override fun onActivityDestroyed(activity: Activity) {
            DensityUtil.resetDensity(activity)

            activity.unbindFragmentLifecycle(fragmentLifecycleCallbacks)
        }
    }

    override fun init() {
        //深色主题适配
        if (!DarkModeTools.get(applicationContext).isSystemTheme()) {
            DarkModeTools.get().updateNightTheme(DarkModeTools.get().isDarkTheme())
        }

        //初始化工具类
        CoroutineScope(Dispatchers.IO).launch {
            SPUtil.getInstance(this@App)
            AppDataBase.getInstance(this@App)
            CacheManager.init(this@App, BuildConfig.VERSION_CODE)
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