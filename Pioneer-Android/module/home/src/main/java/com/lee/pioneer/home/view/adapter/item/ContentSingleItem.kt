package com.lee.pioneer.home.view.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lee.library.adapter.base.BaseViewHolder
import com.lee.library.adapter.item.ViewItem
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.library.extensions.setTextColorCompat
import com.lee.library.utils.TimeUtil
import com.lee.pioneer.library.common.constant.HttpConstant
import com.lee.pioneer.library.common.model.entity.Content
import com.lee.pioneer.library.common.tools.GlideTools
import com.lee.pioneer.library.common.tools.ViewTools
import com.lee.pioneer.R
import com.lee.pioneer.home.R as HR

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容item 单图样式
 */
class ContentSingleItem : ViewItem<Content>() {

    override fun getItemView(context: Context, parent: ViewGroup): View =
        LayoutInflater.from(context).inflate(HR.layout.item_content_single, parent, false)

    override fun openRecycler(): Boolean {
        return true
    }

    override fun isItemView(entity: Content, position: Int): Boolean {
        return entity.images.size == 1
    }

    override fun viewRecycled(holder: BaseViewHolder, entity: Content, position: Int) {
        holder.let {
            it.getView<ImageView>(HR.id.iv_picture)?.run {
                Glide.with(this.context).clear(this)
            }
        }
    }

    override fun convert(holder: BaseViewHolder, entity: Content, position: Int) {
        holder.run {
            val tvAuthor = getView<TextView>(HR.id.tv_author)
            val tvCategory = getView<TextView>(HR.id.tv_category)
            val tvTitle = getView<TextView>(HR.id.tv_title)
            val tvDescription = getView<TextView>(HR.id.tv_description)
            val ivPicture = getView<ImageView>(HR.id.iv_picture)
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
                //设置图片
                GlideTools.get().loadPlaceholderImage(
                    HttpConstant.getCropImagePath(images[0]),
                    R.drawable.shape_theme_placeholder,
                    ivPicture)

                //设置文本
                tvAuthor.text = author
                tvCategory.text = category
                tvTitle.text = title
                tvDescription.text = desc
                tvDescription.maxLines = if (ViewTools.isTextEllipse(tvTitle)) 2 else 3
                tvLike.text =
                    if (entity.likeCounts == 0) tvLike.context.getString(R.string.item_like_text) else likeCounts.toString()
                tvViews.text =
                    if (entity.views == 0) tvLike.context.getString(R.string.item_view_text) else views.toString()
                tvTime.text = TimeUtil.getChineseTimeString2(publishedAt)
            }
        }
    }

}