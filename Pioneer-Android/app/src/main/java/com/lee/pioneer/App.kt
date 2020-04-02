package com.lee.pioneer

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.lee.library.base.BaseApplication
import com.lee.library.utils.DensityUtil
import com.lee.library.utils.LogUtil
import com.lee.library.utils.SPUtil
import com.lee.library.utils.StatusUtil

/**
 * @author jv.lee
 * @date 2020/3/23
 * @description 程序入口
 */
class App : BaseApplication(), Application.ActivityLifecycleCallbacks {

    override fun init() {
        registerActivityLifecycleCallbacks(this)
        SPUtil.getInstance(this)
    }

    override fun unInit() {
        unregisterActivityLifecycleCallbacks(this)
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        StatusUtil.setStatusFontLight2(activity)
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
//        DensityUtil.resetDensity(activity)
    }

}