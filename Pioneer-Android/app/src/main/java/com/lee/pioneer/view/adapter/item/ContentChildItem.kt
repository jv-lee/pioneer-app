package com.lee.pioneer.view.adapter.item

import android.widget.TextView
import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.pioneer.R
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.ContentHistory

/**
 * @author jv.lee
 * @date 2020/4/23
 * @description 用于展示浏览记录 收藏夹的 Content内容
 */
class ContentChildItem : LeeViewItem<ContentHistory> {
    override fun getItemLayout(): Int {
        return R.layout.item_content_child
    }

    override fun openClick(): Boolean {
        return true
    }

    override fun openShake(): Boolean {
        return true
    }

    override fun openRecycler(): Boolean {
        return false
    }

    override fun isItemView(entity: ContentHistory?, position: Int): Boolean {
        return entity != null
    }

    override fun convert(holder: LeeViewHolder?, entity: ContentHistory?, position: Int) {
        holder?.apply {
            entity?.apply {
                getView<TextView>(R.id.tv_title).text = content.title
                getView<TextView>(R.id.tv_description).text = content.desc
                getView<TextView>(R.id.tv_like_count).text =
                    convertView.context.getString(
                        R.string.item_like_text_count, content.likeCounts.toString()
                    )
                getView<TextView>(R.id.tv_view_count).text =
                    convertView.context.getString(
                        R.string.item_view_text_count, content.views.toString()
                    )
            }
        }
    }

    override fun viewRecycled(holder: LeeViewHolder?, entity: ContentHistory?, position: Int) {

    }

}
