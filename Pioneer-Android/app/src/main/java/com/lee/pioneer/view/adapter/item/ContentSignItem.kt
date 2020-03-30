package com.lee.pioneer.view.adapter.item

import android.widget.TextView
import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.pioneer.R
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.tools.GlideTools

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容item 单图样式
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
                GlideTools.get().loadCenterCopy(entity.images[0], holder.getView(R.id.iv_picture))
            }
        }
    }

}