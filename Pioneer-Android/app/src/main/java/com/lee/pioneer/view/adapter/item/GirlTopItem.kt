package com.lee.pioneer.view.adapter.item

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.library.utils.LogUtil
import com.lee.pioneer.R
import com.lee.pioneer.constants.HttpConstant
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.tools.GlideTools

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlTopItem : LeeViewItem<Content> {
    override fun getItemLayout(): Int {
        return R.layout.item_girl_top
    }

    override fun openClick(): Boolean {
        return true
    }

    override fun openShake(): Boolean {
        return true
    }

    override fun openRecycler(): Boolean {
        return true
    }

    override fun isItemView(entity: Content?, position: Int): Boolean {
        return entity != null && entity.viewType == 0
    }

    override fun convert(holder: LeeViewHolder?, entity: Content?, position: Int) {
        holder?.run {
            entity?.images?.get(0)?.let {
                GlideTools.get().loadPlaceholderImage(HttpConstant.getCropImagePath(it), R.drawable.shape_theme_placeholder, getView(R.id.iv_picture))
            }
            getView<TextView>(R.id.tv_description).text = entity?.desc
        }
    }

    override fun viewRecycled(holder: LeeViewHolder?, entity: Content?, position: Int) {
        holder?.let {
            holder.getView<ImageView>(R.id.iv_picture)?.run {
                Glide.with(this.context).clear(this)
            }
        }
    }

}