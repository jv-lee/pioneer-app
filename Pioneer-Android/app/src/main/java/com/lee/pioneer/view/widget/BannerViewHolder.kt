package com.lee.pioneer.view.widget

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.lee.library.widget.banner.holder.MZViewHolder
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.tools.GlideTools

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
        iv.scaleType = ImageView.ScaleType.FIT_XY
        return iv
    }

    override fun onBind(context: Context?, position: Int, data: Banner?) {
        data?.run {
            GlideTools.get().loadCenterCopy(image, iv)
        }
    }

}