package com.lee.pioneer.tools

import android.content.res.ColorStateList
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.lee.pioneer.R


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

        /**
         * 设置viewBackgroundTint方法 向下兼容
         */
        fun setBackgroundSelectorTint(view: View, selectorId: Int) {
            ViewCompat.setBackgroundTintList(
                view,
                ColorStateList.createFromXml(
                    view.resources,
                    view.resources.getXml(selectorId)
                )
            )
        }


    }
}