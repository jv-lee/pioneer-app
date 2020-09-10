package com.lee.library.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.ViewGroup
import com.lee.library.utils.SizeUtil


/**
 * @author jv.lee
 * @date 2020/9/10
 * @description
 */
open class BaseDialog constructor(context: Context, theme: Int) : Dialog(context, theme) {
    override fun show() {
        if (context is Activity) {
            if ((context as Activity).isFinishing) {
                return
            }
        }
        super.show()
    }

    override fun dismiss() {
        if (context is Activity) {
            if ((context as Activity).isFinishing) {
                return
            }
        }
        super.dismiss()
    }

    override fun onBackPressed() {
        if (isShowing) {
            dismiss()
            return
        }
        super.onBackPressed()
    }

}

fun Dialog.setBottomDialog(height: Int) {
    val window = window
    window?.run {
        decorView.setPadding(0, 0, 0, 0)
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        setGravity(Gravity.START or Gravity.BOTTOM)
        val maxHeight = SizeUtil.dp2px(context, height.toFloat())
        if (dm.heightPixels < maxHeight) {
            setLayout(dm.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT)
        } else {
            setLayout(dm.widthPixels, maxHeight)
        }
    }

}