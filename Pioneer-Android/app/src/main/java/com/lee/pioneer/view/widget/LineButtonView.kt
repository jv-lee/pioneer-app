package com.lee.pioneer.view.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import com.lee.library.extensions.setImageTintCompat
import com.lee.library.utils.SizeUtil
import com.lee.pioneer.R

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class LineButtonView : ConstraintLayout {

    private var iconSize: Int = 0
    private var itemTextSize: Float = 0F
    private var itemTextMargin: Int = 0
    private var itemTextColor: Int = 0
    private lateinit var itemText: String
    private var leftDrawableId = 0
    private var rightDrawableId = 0
    private var leftTint: Int = 0
    private var rightTint: Int = 0

    private lateinit var tvText: TextView
    private lateinit var leftDrawable: ImageView
    private lateinit var rightDrawable: ImageView

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributes: AttributeSet) : this(context, attributes, 0)
    constructor(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributes,
        defStyleAttr
    ) {
        initAttribute(attributes!!)
        initView()
    }

    private fun initAttribute(attrs: AttributeSet) {

        context.obtainStyledAttributes(attrs, R.styleable.LineButtonView).run {
            iconSize =
                getDimensionPixelSize(
                    R.styleable.LineButtonView_iconSize,
                    resources.getDimensionPixelSize(R.dimen.view_line_icon_size)
                )
            itemTextSize = getDimension(
                R.styleable.LineButtonView_itemTextSize,
                SizeUtil.dimensToSp(context, resources.getDimension(R.dimen.view_line_text_size))
            )
            itemTextMargin = getDimensionPixelSize(
                R.styleable.LineButtonView_itemTextMargin,
                resources.getDimension(R.dimen.view_line_text_margin).toInt()
            )
            itemTextColor = getColor(
                R.styleable.LineButtonView_itemTextColor,
                ContextCompat.getColor(context, R.color.colorThemeAccent)
            )
            itemText = getString(R.styleable.LineButtonView_itemText) ?: ""
            leftDrawableId = getResourceId(R.styleable.LineButtonView_leftDrawable, 0)
            rightDrawableId = getResourceId(R.styleable.LineButtonView_rightDrawable, 0)
            leftTint = getColor(
                R.styleable.LineButtonView_leftTint,
                0
            )
            rightTint = getColor(
                R.styleable.LineButtonView_rightTint,
                0
            )

            recycle()
        }
    }

    private fun initView() {
        tvText = TextView(context)
        tvText.run {
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            updateLayoutParams<ConstraintLayout.LayoutParams> {
                startToStart = 0
                topToTop = 0
                bottomToBottom = 0
                leftMargin = itemTextMargin
            }
            setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                itemTextSize
            )
            setTextColor(itemTextColor)
            text = itemText
            addView(this)
        }

        leftDrawable = ImageView(context)
        leftDrawable.run {
            layoutParams = LayoutParams(iconSize, iconSize)
            updateLayoutParams<ConstraintLayout.LayoutParams> {
                startToStart = 0
                topToTop = 0
                bottomToBottom = 0
            }
            //设置兼容资源及调色
            setImageTintCompat(leftDrawableId, leftTint)
            addView(this)
        }

        rightDrawable = ImageView(context)
        rightDrawable.run {
            layoutParams = LayoutParams(iconSize, iconSize)
            updateLayoutParams<ConstraintLayout.LayoutParams> {
                endToEnd = 0
                topToTop = 0
                bottomToBottom = 0
            }
            //设置兼容资源及调色
            setImageTintCompat(rightDrawableId, rightTint)
            addView(this)
        }
    }

}