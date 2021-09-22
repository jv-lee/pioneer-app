package com.lee.pioneer.me.viewmodel

import androidx.databinding.ObservableField
import com.lee.library.mvvm.base.CoroutineViewModel
import com.lee.library.tools.DarkModeTools

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class MeViewModel : CoroutineViewModel() {

    val totalCacheStr = ObservableField<String>("")
    val isSystem = ObservableField<Boolean>(DarkModeTools.get().isSystemTheme())
}