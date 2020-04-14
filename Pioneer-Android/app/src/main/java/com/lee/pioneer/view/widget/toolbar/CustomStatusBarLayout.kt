package com.lee.pioneer.view.widget.toolbar

import android.content.Context
import android.util.AttributeSet
import com.lee.library.utils.StatusUtil

/**
 * @author jv.lee
 * @date 2020/4/14
 * @description
 */
class CustomStatusBarLayout : CustomToolbarLayout {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributes: AttributeSet) : this(context, attributes, 0)
    constructor(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributes,
        defStyleAttr
    )

    override fun initStatusBarPadding() {}

    override fun initLayoutHeight(): Int {
        return StatusUtil.getStatusBarHeight(context)
    }

}