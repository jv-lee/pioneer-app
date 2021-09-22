package com.lee.pioneer.home.view.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.lee.library.adapter.binding.ViewBindingHolder
import com.lee.library.adapter.item.ViewBindingItem
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.library.extensions.setTextColorCompat
import com.lee.library.utils.TimeUtil
import com.lee.pioneer.home.R
import com.lee.pioneer.home.databinding.ItemContentTextBinding
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.tools.CommonTools

/**
 * @author jv.lee
 * @date 2020/3/31
 * @description 内容item 无图样式
 */
class ContentTextItem : ViewBindingItem<Content>() {

    override fun getItemViewBinding(context: Context, parent: ViewGroup): ViewBinding {
        return ItemContentTextBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun isItemView(entity: Content, position: Int): Boolean {
        return entity.images.isNullOrEmpty()
    }

    override fun convert(holder: ViewBindingHolder, entity: Content, position: Int) {
        holder.getViewBinding<ItemContentTextBinding>().run {
            holder.itemView.setBackgroundColorCompat(R.color.colorThemeItem)
            tvAuthor.setTextColorCompat(R.color.colorPrimaryDark)
            tvCategory.setTextColorCompat(R.color.colorPrimary)
            tvTitle.setTextColorCompat(R.color.colorAccent)
            tvDescription.setTextColorCompat(R.color.colorPrimaryDark)
            tvLike.setTextColorCompat(R.color.colorPrimary)
            tvView.setTextColorCompat(R.color.colorPrimary)
            tvTime.setTextColorCompat(R.color.colorPrimaryDark)

            entity.run {
                tvAuthor.text = author
                tvCategory.text = category
                tvTitle.text = title
                tvDescription.text = desc
                tvDescription.maxLines = if (CommonTools.isTextEllipse(tvTitle)) 2 else 3
                tvLike.text =
                    if (entity.likeCounts == 0) tvLike.context.getString(R.string.item_like_text) else likeCounts.toString()
                tvView.text =
                    if (entity.views == 0) tvLike.context.getString(R.string.item_view_text) else views.toString()
                tvTime.text = TimeUtil.getChineseTimeString2(publishedAt)

            }
        }
    }

}