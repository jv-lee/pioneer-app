package com.lee.pioneer.view.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import com.lee.library.utils.SizeUtil
import com.lee.pioneer.R

/**
 * @author jv.lee
 * @date 2020/4/1
 * @description
 */
class AppTitleBar : CustomToolbarLayout {

    var ivBack: ImageView? = null
    var ivMenu: ImageView? = null
    var tvTitle: TextView? = null

    private var titleText: String? = null
    private var backRes: Int? = null
    private var menuRes: Int? = null
    private var titleEnable: Int? = null
    private var backEnable: Int? = null
    private var menuEnable: Int? = null

    private var clickListener: ClickListener? = null

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributes: AttributeSet) : this(context, attributes, 0)
    constructor(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributes,
        defStyleAttr
    ) {
        initAttr(attributes!!)
        initView()
    }

    private fun initAttr(attrs: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.AppTitleBar)

        titleText = typeArray.getString(R.styleable.AppTitleBar_titleText)
        backRes = typeArray.getResourceId(R.styleable.AppTitleBar_backRes, R.drawable.vector_back)
        menuRes = typeArray.getResourceId(R.styleable.AppTitleBar_menuRes, R.drawable.vector_menu)
        titleEnable = typeArray.getInt(R.styleable.AppTitleBar_titleEnable, View.VISIBLE)
        backEnable = typeArray.getInt(R.styleable.AppTitleBar_backEnable, View.VISIBLE)
        menuEnable = typeArray.getInt(R.styleable.AppTitleBar_menuEnable, View.VISIBLE)
        typeArray.recycle()
    }

    @SuppressLint("ResourceType")
    private fun initView() {
        ivBack = ImageView(context)
        ivBack?.run {
            layoutParams =
                LayoutParams(
                    resources.getDimension(R.dimen.toolbar_button_width).toInt(),
                    MATCH_PARENT
                )
            updateLayoutParams<ConstraintLayout.LayoutParams> { startToStart = 0 }
            scaleType = ImageView.ScaleType.CENTER
            backRes?.let { setImageResource(it) }
            backEnable?.let { visibility = it }
            setOnClickListener { clickListener?.backClick() }
            addView(ivBack)
        }


        ivMenu = ImageView(context)
        ivMenu?.run {
            layoutParams =
                LayoutParams(
                    resources.getDimension(R.dimen.toolbar_button_width).toInt(),
                    MATCH_PARENT
                )
            updateLayoutParams<ConstraintLayout.LayoutParams> { endToEnd = 0 }
            scaleType = ImageView.ScaleType.CENTER
            menuRes?.let { setImageResource(it) }
            menuEnable?.let { visibility = it }
            setOnClickListener { clickListener?.menuClick() }
            addView(ivMenu)
        }

        tvTitle = TextView(context)
        tvTitle?.run {
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            updateLayoutParams<ConstraintLayout.LayoutParams> {
                startToStart = 0
                endToEnd = 0
                topToTop = 0
                bottomToBottom = 0
            }
            setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            titleText?.let { text = it }
            titleEnable?.let { visibility = it }
            textSize = SizeUtil.px2sp(context,resources.getDimension(R.dimen.font_size_medium)).toFloat()
            addView(tvTitle)
        }

    }

    open class ClickListener {
        open fun backClick(){}
        open fun menuClick(){}
    }

    fun addClickListener(clickListener: AppTitleBar.ClickListener) {
        this.clickListener = clickListener
    }

}