package com.lee.library.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.lee.library.R
import kotlin.math.abs

/**
 * @author jv.lee
 * @date 2020/9/16
 * @description 阴影容器
 */
class ShadowLayout(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private val TAG = "ShadowLayout"

    private var mWidth = 0F
    private var mHeight = 0F
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mRectF = RectF()

    private var shadowRound: Float
    private var shadowBlur: Float
    private var shadowColor: Int
    private var shadowFillColor: Int
    private var shadowOffsetX: Float
    private var shadowOffsetY: Float


    init {
        context.obtainStyledAttributes(attributeSet, R.styleable.ShadowLayout).run {
            shadowRound = getDimension(R.styleable.ShadowLayout_shadowRound, 10F)
            shadowBlur = getDimension(R.styleable.ShadowLayout_shadowBlur, 10F)
            shadowColor = getColor(
                R.styleable.ShadowLayout_shadowColor,
                ContextCompat.getColor(context, android.R.color.black)
            )
            shadowFillColor = getColor(
                R.styleable.ShadowLayout_shadowFillColor,
                ContextCompat.getColor(context, android.R.color.white)
            )
            shadowOffsetX = getDimension(R.styleable.ShadowLayout_shadowOffsetX, 0F)
            shadowOffsetY = getDimension(R.styleable.ShadowLayout_shadowOffsetY, 0F)

            recycle()
        }
        setWillNotDraw(false)
        initPaint()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w - shadowBlur
        mHeight = h - shadowBlur
        initRectF()
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var widthSize = measuredWidth
        var heightSize = measuredHeight

//        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
//        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
//
//        //自适应模式调整宽高 加上阴影所占的值
//        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.EXACTLY) {
//            widthSize += (shadowSize.toInt() * 2)
//        }
//        if (heightMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.EXACTLY) {
//            heightSize += (shadowSize.toInt() * 2)
//        }

        widthSize += (shadowBlur.toInt() * 2)
        heightSize += (shadowBlur.toInt() * 2)

        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRoundRect(mRectF, shadowRound, shadowRound, mPaint)
    }

    /**
     * 根据offset 调整padding值
     */
    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        val size = shadowBlur.toInt()
        super.setPadding(left + size, top + size, right + size, bottom + size)
    }

    private fun initPaint() {
        mPaint.color = shadowFillColor
        mPaint.style = Paint.Style.FILL
        mPaint.setShadowLayer(shadowBlur, shadowOffsetX, shadowOffsetY, shadowColor)
    }

    /**
     * 根据 offset 调整绘制区域
     */
    private fun initRectF() {
        if (shadowOffsetY > 0) {
            mRectF.top = shadowBlur - shadowOffsetY
            mRectF.bottom = mHeight - shadowOffsetY
        } else {
            mRectF.top = shadowBlur + abs(shadowOffsetY)
            mRectF.bottom = mHeight + abs(shadowOffsetY)
        }
        if (shadowOffsetX > 0) {
            mRectF.left = shadowBlur - shadowOffsetX
            mRectF.right = mWidth - shadowOffsetX
        } else {
            mRectF.left = shadowBlur + abs(shadowOffsetX)
            mRectF.right = mWidth + abs(shadowOffsetX)
        }

    }

}