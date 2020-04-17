package com.lee.pioneer.view.adapter.item

import android.widget.TextView
import com.bumptech.glide.Glide
import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.library.utils.LogUtil
import com.lee.library.widget.ImageViewRound
import com.lee.pioneer.R
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.tools.GlideTools

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlBottomItem : LeeViewItem<Content> {
    override fun getItemLayout(): Int {
        return R.layout.item_girl_bottom
    }

    override fun openClick(): Boolean {
        return true
    }

    override fun openShake(): Boolean {
        return true
    }

    override fun isItemView(entity: Content?, position: Int): Boolean {
        return entity != null && entity.viewType != 0
    }

    override fun convert(holder: LeeViewHolder?, entity: Content?, position: Int) {
        holder?.let {
            entity?.images?.get(0)?.let {
                GlideTools.get().loadBigImage(it, holder.getView(R.id.iv_picture))
            }
            holder.getView<TextView>(R.id.tv_description).text = entity?.desc
        }
    }

    override fun viewRecycled(holder: LeeViewHolder?, entity: Content?, position: Int) {
        holder?.let {
            holder.getView<ImageViewRound>(R.id.iv_picture)?.run {
                Glide.with(this.context).clear(this)
            }
        }
    }

}