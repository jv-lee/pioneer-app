package com.lee.pioneer.view.adapter.item

import android.widget.ImageView
import android.widget.TextView
import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.library.utils.TimeUtil
import com.lee.pioneer.R
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.tools.GlideTools
import com.lee.pioneer.tools.ViewTools

/**
 * @author jv.lee
 * @date 2020/3/31
 * @description 内容item 多图样式
 */
class ContentMultipleItem : LeeViewItem<Content> {

    override fun getItemLayout(): Int {
        return R.layout.item_content_multiple
    }

    override fun openClick(): Boolean {
        return true
    }

    override fun openShake(): Boolean {
        return true
    }

    override fun isItemView(entity: Content?, position: Int): Boolean {
        return entity?.images?.size!! > 1
    }

    override fun convert(holder: LeeViewHolder?, entity: Content?, position: Int) {
        holder?.run {
            val ivPicture = getView<ImageView>(R.id.iv_picture)
            val ivPicture2 = getView<ImageView>(R.id.iv_picture2)
            val ivPicture3 = getView<ImageView>(R.id.iv_picture3)
            val tvAuthor = getView<TextView>(R.id.tv_author)
            val tvCategory = getView<TextView>(R.id.tv_category)
            val tvTitle = getView<TextView>(R.id.tv_title)
            val tvDescription = getView<TextView>(R.id.tv_description)
            val tvLike = getView<TextView>(R.id.tv_like)
            val tvViews = getView<TextView>(R.id.tv_view)
            val tvTime = getView<TextView>(R.id.tv_time)
            entity?.run {
                //设置图片
                val pictureRadius =
                    ivPicture.context.resources.getDimension(R.dimen.item_picture_radius).toInt()
                GlideTools.get().loadCenterCopy(images[0], ivPicture)
                GlideTools.get().loadCenterCopy(images[1], ivPicture2)
                if (images.size > 2) {
                    GlideTools.get().loadCenterCopy(images[2], ivPicture3)
                } else {
                    ivPicture3.setImageDrawable(null)
                }

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