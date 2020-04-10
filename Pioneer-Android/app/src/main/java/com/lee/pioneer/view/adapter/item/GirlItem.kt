package com.lee.pioneer.view.adapter.item

import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.pioneer.R
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.tools.GlideTools

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlItem : LeeViewItem<Content> {
    override fun getItemLayout(): Int {
        return R.layout.item_girl
    }

    override fun openClick(): Boolean {
        return true
    }

    override fun openShake(): Boolean {
        return true
    }

    override fun isItemView(entity: Content?, position: Int): Boolean {
        return entity != null
    }

    override fun convert(holder: LeeViewHolder?, entity: Content?, position: Int) {
        holder?.let {
            entity?.images?.get(0)?.let {
                GlideTools.get().loadCenterCopy(it, holder.getView(R.id.iv_picture))
            }
        }

    }

}