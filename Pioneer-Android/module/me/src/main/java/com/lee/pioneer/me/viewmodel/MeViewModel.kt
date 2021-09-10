package com.lee.pioneer.me.viewmodel

import android.widget.CompoundButton
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.tools.DarkModeTools

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class MeViewModel : BaseViewModel() {

    val totalCacheStr = ObservableField<String>("")
    val isSystem = ObservableField<Boolean>(DarkModeTools.get().isSystemTheme())
    val isDark = ObservableField<Boolean>(DarkModeTools.get().isDarkTheme())
    val liveSystem by lazy { MutableLiveData<Boolean>() }
    val liveDark by lazy { MutableLiveData<Boolean>() }

    val systemSwitchChange = CompoundButton.OnCheckedChangeListener { view, check ->
        isSystem.set(check)
        DarkModeTools.get().updateSystemTheme(check)
    }

    val darkSwitchChange = CompoundButton.OnCheckedChangeListener { view, check ->
        DarkModeTools.get().updateNightTheme(check)
    }
}