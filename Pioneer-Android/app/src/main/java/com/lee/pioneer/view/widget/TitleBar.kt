package com.lee.pioneer.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import com.lee.pioneer.R

/**
 * @author jv.lee
 * @date 2020/4/1
 * @description
 */
class TitleBar : CustomToolbarLayout {

    private lateinit var ivBack: ImageView
    private lateinit var ivMenu: ImageView
    private lateinit var tvTitle: TextView

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attributes: AttributeSet) : this(context, attributes, 0)
    constructor(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributes,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        ivBack = ImageView(context)
        ivBack.run {
            layoutParams =
                LayoutParams(
                    resources.getDimension(R.dimen.toolbar_button_width).toInt(),
                    MATCH_PARENT
                )
            updateLayoutParams<ConstraintLayout.LayoutParams> { startToStart = 0 }
            scaleType = ImageView.ScaleType.CENTER
            setImageResource(R.drawable.vector_back)
            addView(ivBack)
        }


        ivMenu = ImageView(context)
        ivMenu.run {
            layoutParams =
                LayoutParams(
                    resources.getDimension(R.dimen.toolbar_button_width).toInt(),
                    MATCH_PARENT
                )
            updateLayoutParams<ConstraintLayout.LayoutParams> { endToEnd = 0 }
            scaleType = ImageView.ScaleType.CENTER
            setImageResource(R.drawable.vector_menu)
            addView(ivMenu)
        }

        tvTitle = TextView(context)
        tvTitle.run {
            tvTitle.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            updateLayoutParams<ConstraintLayout.LayoutParams> {
                startToStart = 0
                endToEnd = 0
                topToTop = 0
                bottomToBottom = 0
            }
            setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
            text = "Title"
            addView(tvTitle)
        }

    }

}