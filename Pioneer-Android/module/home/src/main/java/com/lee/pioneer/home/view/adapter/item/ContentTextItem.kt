package com.lee.pioneer.home.view.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lee.library.adapter.base.BaseViewHolder
import com.lee.library.adapter.item.ViewItem
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.library.extensions.setTextColorCompat
import com.lee.library.utils.TimeUtil
import com.lee.pioneer.R
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.tools.CommonTools
import com.lee.pioneer.home.R as HR

/**
 * @author jv.lee
 * @date 2020/3/31
 * @description 内容item 无图样式
 */
class ContentTextItem : ViewItem<Content>() {

    override fun getItemView(context: Context, parent: ViewGroup): View =
        LayoutInflater.from(context).inflate(HR.layout.item_content_text, parent, false)

    override fun isItemView(entity: Content, position: Int): Boolean {
        return entity.images.isNullOrEmpty()
    }

    override fun convert(holder: BaseViewHolder, entity: Content, position: Int) {
        holder.run {
            val tvAuthor = getView<TextView>(HR.id.tv_author)
            val tvCategory = getView<TextView>(HR.id.tv_category)
            val tvTitle = getView<TextView>(HR.id.tv_title)
            val tvDescription = getView<TextView>(HR.id.tv_description)
            val tvLike = getView<TextView>(HR.id.tv_like)
            val tvViews = getView<TextView>(HR.id.tv_view)
            val tvTime = getView<TextView>(HR.id.tv_time)

            holder.itemView.setBackgroundColorCompat(R.color.colorThemeItem)
            tvAuthor.setTextColorCompat(R.color.colorPrimaryDark)
            tvCategory.setTextColorCompat(R.color.colorPrimary)
            tvTitle.setTextColorCompat(R.color.colorAccent)
            tvDescription.setTextColorCompat(R.color.colorPrimaryDark)
            tvLike.setTextColorCompat(R.color.colorPrimary)
            tvViews.setTextColorCompat(R.color.colorPrimary)
            tvTime.setTextColorCompat(R.color.colorPrimaryDark)

            entity.run {
                tvAuthor.text = author
                tvCategory.text = category
                tvTitle.text = title
                tvDescription.text = desc
                tvDescription.maxLines = if (CommonTools.isTextEllipse(tvTitle)) 2 else 3
                tvLike.text =
                    if (entity.likeCounts == 0) tvLike.context.getString(R.string.item_like_text) else likeCounts.toString()
                tvViews.text =
                    if (entity.views == 0) tvLike.context.getString(R.string.item_view_text) else views.toString()
                tvTime.text = TimeUtil.getChineseTimeString2(publishedAt)

            }
        }
    }

}