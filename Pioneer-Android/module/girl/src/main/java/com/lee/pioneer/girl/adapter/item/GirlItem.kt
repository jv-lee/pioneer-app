package com.lee.pioneer.girl.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.lee.library.adapter.base.BaseViewHolder
import com.lee.library.adapter.item.ViewItem
import com.lee.library.widget.ShadowConstraintLayout
import com.lee.pioneer.constants.HttpConstant
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.tools.GlideTools
import com.lee.pioneer.R
import com.lee.pioneer.girl.R as GR

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlItem : ViewItem<Content>() {

    override fun getItemView(context: Context, parent: ViewGroup): View =
        LayoutInflater.from(context).inflate(GR.layout.item_girl, parent, false)

    override fun openRecycler(): Boolean {
        return true
    }

    override fun isItemView(entity: Content, position: Int): Boolean {
        return entity.viewType == 0
    }

    override fun viewRecycled(holder: BaseViewHolder, entity: Content, position: Int) {
        holder.getView<ImageView>(GR.id.iv_picture)?.run {
            Glide.with(this.context).clear(this)
        }
    }

    override fun convert(holder: BaseViewHolder, entity: Content, position: Int) {
        holder.run {
            entity.images.takeIf { it.isNotEmpty() }?.get(0)?.let {
                GlideTools.get().loadPlaceholderImage(
                    HttpConstant.getCropImagePath(it),
                    R.drawable.shape_theme_placeholder,
                    getView(GR.id.iv_picture)
                )
            }

            getView<TextView>(GR.id.tv_description).text = entity.desc
            getView<TextView>(GR.id.tv_description).setTextColor(
                ContextCompat.getColor(
                    holder.convertView?.context!!,
                    R.color.colorThemePrimary
                )
            )
            getView<ShadowConstraintLayout>(GR.id.shadow_container).setShadowFillColor(
                ContextCompat.getColor(
                    holder.convertView?.context!!,
                    R.color.colorThemeItem
                )
            )
        }
    }


}