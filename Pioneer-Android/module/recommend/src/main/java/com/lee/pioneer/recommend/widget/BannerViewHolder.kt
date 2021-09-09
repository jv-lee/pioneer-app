package com.lee.pioneer.recommend.widget

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.lee.library.widget.banner.holder.MZViewHolder
import com.lee.pioneer.R
import com.lee.pioneer.library.common.model.entity.Banner
import com.lee.pioneer.library.common.tools.GlideTools

/**
 * @author jv.lee
 * @date 2020/4/9
 * @description
 */
class BannerViewHolder : MZViewHolder<Banner> {

    private lateinit var iv: ImageView

    override fun createView(context: Context?): View {
        iv = ImageView(context)
        iv.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        iv.scaleType = ImageView.ScaleType.CENTER_CROP
        context?.let {
            iv.setBackgroundColor(ContextCompat.getColor(it, R.color.colorThemePlaceholder))
        }
        return iv
    }

    override fun onBind(context: Context?, position: Int, data: Banner?) {
        data?.run {
            GlideTools.get().loadImage(image, iv)
        }
    }

}