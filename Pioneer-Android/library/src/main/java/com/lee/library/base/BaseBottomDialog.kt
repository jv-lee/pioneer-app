package com.lee.library.base

import android.content.Context
import com.lee.library.R

/**
 * @author jv.lee
 * @date 2020/9/10
 * @description
 */
class BaseBottomDialog constructor(context: Context) :
    BaseDialog(context, R.style.BottomDialogTheme) {
    init {
        setBottomDialog(500)
        setContentView(0)
    }
}