package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.utils.CacheUtil
import com.lee.pioneer.App

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class MeViewModel(application: Application) : BaseViewModel(application) {

    val totalCacheStr = ObservableField<String>("")
    val clearObserver = MutableLiveData<Boolean>()

    fun clearCache() {
        if (CacheUtil.clearAllCache((getApplication() as App).baseContext)) {
            setCacheStr()
            clearObserver.value = true
        } else {
            clearObserver.value = false
        }
    }

    fun setCacheStr() {
        totalCacheStr.set(CacheUtil.getTotalCacheSize((getApplication() as App).baseContext))
    }


}