package com.lee.pioneer

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import com.lee.library.base.BaseApplication
import com.lee.library.cache.CacheManager
import com.lee.library.utils.DensityUtil
import com.lee.library.utils.SPUtil
import com.lee.library.utils.StatusUtil
import com.lee.pioneer.db.AppDataBase
import com.lee.pioneer.tools.DarkModeTools
import com.lee.pioneer.tools.SimpleActivityLifecycleCallbacks
import com.squareup.leakcanary.LeakCanary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020/3/23
 * @description 程序入口
 */
class App : BaseApplication() {

    private val activityLifecycleCallbacks = object : SimpleActivityLifecycleCallbacks() {

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            StatusUtil.setNavigationBarColor(activity, Color.BLACK)
            if (DarkModeTools.get().isDarkTheme()) {
                StatusUtil.setLightStatusIcon(activity)
            } else {
                StatusUtil.setDarkStatusIcon(activity)
            }
        }

        override fun onActivityStarted(activity: Activity) {
            super.onActivityStarted(activity)
            DensityUtil.setDensity(activity)
        }

        override fun onActivityDestroyed(activity: Activity) {
            DensityUtil.resetDensity(activity)
        }
    }

    override fun init() {
        //深色主题适配
        if (!DarkModeTools.get(applicationContext).isSystemTheme()) {
            DarkModeTools.get().updateNightTheme(DarkModeTools.get().isDarkTheme())
        }

        //初始化工具类
        GlobalScope.launch(Dispatchers.IO) {
            SPUtil.getInstance(this@App)
            AppDataBase.getInstance(this@App)
            CacheManager.init(this@App, BuildConfig.VERSION_CODE)
        }

        //注册Activity生命周期监听
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)

        //初始化内存检测工具
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
    }

    override fun unInit() {
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

}