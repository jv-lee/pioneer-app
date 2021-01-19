package com.lee.pioneer.tools

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import com.imagetools.select.R

/**
 * @author jv.lee
 * @date 2020/3/31
 * @description
 */
class ViewTools {

    companion object {

        /**
         * 计算textView是否换行
         */
        fun isTextEllipse(text: TextView): Boolean {
            val dm = text.resources.displayMetrics
            text.measure(
                View.MeasureSpec.makeMeasureSpec(dm.widthPixels, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(dm.heightPixels, View.MeasureSpec.AT_MOST)
            )
            val maxWidth = text.maxWidth
            val textWidth = text.paint.measureText(text.text.toString())
            return textWidth > maxWidth
        }

        fun getItemOrderAnimator(context: Context): LayoutAnimationController {
            val animController =
                LayoutAnimationController(AnimationUtils.loadAnimation(context, R.anim.item_alpha_in))
            animController.order = LayoutAnimationController.ORDER_NORMAL
            animController.delay = 0.1f
            return animController
        }

    }
}