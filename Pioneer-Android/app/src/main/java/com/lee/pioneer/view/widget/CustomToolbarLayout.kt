package com.lee.pioneer.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.lee.library.utils.StatusUtil
import com.lee.pioneer.R


/**
 * @author jv.lee
 * @date 2020/4/1
 * @description 自定义toolbar容器
 */
open class CustomToolbarLayout : ConstraintLayout {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributes: AttributeSet) : this(context, attributes, 0)
    constructor(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributes,
        defStyleAttr
    ) {
        initStyle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //设置默认宽高比 适配沉浸式
        val toolbarHeight = resources.getDimension(R.dimen.toolbar_height).toInt()
        val statusHeight = StatusUtil.getStatusBarHeight(context)
        setMeasuredDimension(MATCH_PARENT, toolbarHeight + statusHeight)
    }

    private fun initStyle() {
        //设置默认背景色
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorThemeItem))
        val statusHeight = StatusUtil.getStatusBarHeight(context)
        setPadding(0, context.resources.getDimension(R.dimen.toolbar_padding).toInt(), 0, 0)
        setPadding(
            0,
            statusHeight,
            0,
            0
        )
    }

}