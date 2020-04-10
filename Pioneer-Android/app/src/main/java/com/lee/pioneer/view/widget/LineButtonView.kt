package com.lee.pioneer.view.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import com.lee.library.utils.SizeUtil
import com.lee.pioneer.R

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class LineButtonView : ConstraintLayout {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributes: AttributeSet) : this(context, attributes, 0)
    constructor(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributes,
        defStyleAttr
    ) {
        initAttribute()
        initStyle()
        initView()
    }

    private fun initAttribute() {

    }

    private fun initStyle() {
        setPadding(resources.getDimensionPixelSize(R.dimen.padding_large))
        background =
            ContextCompat.getDrawable(context, R.drawable.shape_line_button_default_background)
    }

    private fun initView() {
        val iconSize = resources.getDimension(R.dimen.view_line_icon_size).toInt()
        val textMargin = resources.getDimension(R.dimen.view_line_text_margin).toInt()
        val textSize = SizeUtil.dimensToSp(context,resources.getDimension(R.dimen.view_line_text_size))
        val ivIcon = ImageView(context)
        ivIcon.run {
            layoutParams = LayoutParams(iconSize, iconSize)
            updateLayoutParams<ConstraintLayout.LayoutParams> {
                startToStart = 0
                topToTop = 0
                bottomToBottom = 0
            }
            setImageResource(R.mipmap.ic_launcher)
            addView(this)
        }

        val tvText = TextView(context)
        tvText.run {
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            updateLayoutParams<ConstraintLayout.LayoutParams> {
                startToStart = 0
                topToTop = 0
                bottomToBottom = 0
                leftMargin = textMargin
            }
            setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                textSize)
            setTextColor(ContextCompat.getColor(context, R.color.colorThemeAccent))
            text = "历史记录"
            addView(this)
        }

        val ivArrow = ImageView(context)
        ivArrow.run {
            layoutParams = LayoutParams(iconSize, iconSize)
            updateLayoutParams<ConstraintLayout.LayoutParams> {
                endToEnd = 0
                topToTop = 0
                bottomToBottom = 0
            }
            setImageResource(R.drawable.vector_arrow)
            addView(this)
        }
    }
}