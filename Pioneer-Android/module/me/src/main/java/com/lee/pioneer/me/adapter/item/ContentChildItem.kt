package com.lee.pioneer.me.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lee.library.adapter.base.BaseViewHolder
import com.lee.library.adapter.item.ViewItem
import com.lee.pioneer.library.common.entity.ContentHistory
import com.lee.pioneer.me.R

/**
 * @author jv.lee
 * @date 2020/4/23
 * @description 用于展示浏览记录 收藏夹的 Content内容
 */
class ContentChildItem : ViewItem<ContentHistory>() {

    override fun getItemView(context: Context, parent: ViewGroup): View =
        LayoutInflater.from(context).inflate(R.layout.item_content_child, parent, false)

    override fun isItemView(entity: ContentHistory, position: Int): Boolean {
        return true
    }

    override fun convert(holder: BaseViewHolder, entity: ContentHistory, position: Int) {
        holder.run {
            entity.run {
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

}
