package com.lee.pioneer

import android.app.Activity
import android.os.Bundle
import com.lee.library.base.BaseApplication
import com.lee.library.cache.CacheManager
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
            if (DarkModeTools.get().isDarkTheme()) {
                StatusUtil.setLightStatusIcon(activity)
            } else {
                StatusUtil.setDarkStatusIcon(activity)
            }
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
            CacheManager.getInstance(this@App, BuildConfig.VERSION_CODE)
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