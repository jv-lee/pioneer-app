package com.lee.library.mvvm.load

import androidx.annotation.IntDef
import com.lee.library.mvvm.load.LoadStatus.Companion.LOAD_MORE
import com.lee.library.mvvm.load.LoadStatus.Companion.REFRESH
import com.lee.library.mvvm.load.LoadStatus.Companion.RELOAD

/**
 * @author jv.lee
 * @date 2020/11/25
 * @description
 */
@IntDef(REFRESH, LOAD_MORE, RELOAD)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class LoadStatus {

    companion object {
        const val INIT: Int = 0x0000
        const val REFRESH: Int = 0x001
        const val LOAD_MORE: Int = 0x002
        const val RELOAD: Int = 0x003
    }
}