package com.lee.pioneer.view.item

import android.widget.TextView
import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.pioneer.R
import com.lee.pioneer.model.entity.Content

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description
 */
class ContentSignItem : LeeViewItem<Content> {
    override fun getItemLayout(): Int {
        return R.layout.item_content_sign
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
        holder?.run {
            entity?.run {
                holder.getView<TextView>(R.id.tv_title).text = entity.title
            }
        }
    }

}