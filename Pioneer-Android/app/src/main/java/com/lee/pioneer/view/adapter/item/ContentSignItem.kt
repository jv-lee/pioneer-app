package com.lee.pioneer.view.adapter.item

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.library.utils.TimeUtil
import com.lee.pioneer.R
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.tools.GlideTools
import com.lee.pioneer.tools.ViewTools

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

    override fun openRecycler(): Boolean {
        return true
    }

    override fun isItemView(entity: Content?, position: Int): Boolean {
        return entity?.images?.size == 1
    }

    override fun convert(holder: LeeViewHolder?, entity: Content?, position: Int) {
        holder?.run {
            val tvAuthor = getView<TextView>(R.id.tv_author)
            val tvCategory = getView<TextView>(R.id.tv_category)
            val tvTitle = getView<TextView>(R.id.tv_title)
            val tvDescription = getView<TextView>(R.id.tv_description)
            val ivPicture = getView<ImageView>(R.id.iv_picture)
            val tvLike = getView<TextView>(R.id.tv_like)
            val tvViews = getView<TextView>(R.id.tv_view)
            val tvTime = getView<TextView>(R.id.tv_time)
            entity?.run {
                //设置图片
//                GlideTools.get().loadImage(images[0], ivPicture)

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

    override fun viewRecycled(holder: LeeViewHolder?, entity: Content?, position: Int) {
        holder?.let {
            it.getView<ImageView>(R.id.iv_picture)?.run {
                Glide.with(this.context).clear(this)
            }
        }
    }

}