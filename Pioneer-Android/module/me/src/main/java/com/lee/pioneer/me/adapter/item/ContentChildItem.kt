package com.lee.pioneer.me.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.lee.library.adapter.binding.ViewBindingHolder
import com.lee.library.adapter.item.ViewBindingItem
import com.lee.pioneer.library.common.entity.ContentHistory
import com.lee.pioneer.me.R
import com.lee.pioneer.me.databinding.ItemContentChildBinding

/**
 * @author jv.lee
 * @date 2020/4/23
 * @description 用于展示浏览记录 收藏夹的 Content内容
 */
class ContentChildItem : ViewBindingItem<ContentHistory>() {

    override fun getItemViewBinding(context: Context, parent: ViewGroup): ViewBinding {
        return ItemContentChildBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun isItemView(entity: ContentHistory, position: Int): Boolean {
        return true
    }

    override fun convert(holder: ViewBindingHolder, entity: ContentHistory, position: Int) {
        holder.getViewBinding<ItemContentChildBinding>().run {
            entity.run {
                tvTitle.text = content.title
                tvDescription.text = content.desc
                tvLikeCount.text =
                    holder.convertView.context.getString(
                        R.string.item_like_text_count, content.likeCounts.toString()
                    )
                tvViewCount.text =
                    holder.convertView.context.getString(
                        R.string.item_view_text_count, content.views.toString()
                    )
            }
        }
    }

}
