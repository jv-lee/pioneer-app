package com.lee.pioneer.home.view.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.lee.library.adapter.binding.ViewBindingHolder
import com.lee.library.adapter.item.ViewBindingItem
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.library.extensions.setTextColorCompat
import com.lee.library.utils.TimeUtil
import com.lee.pioneer.home.R
import com.lee.pioneer.home.databinding.ItemContentSingleBinding
import com.lee.pioneer.library.common.constant.HttpConstant
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.tools.CommonTools
import com.lee.pioneer.library.common.tools.GlideTools

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容item 单图样式
 */
class ContentSingleItem : ViewBindingItem<Content>() {

    override fun openRecycler(): Boolean {
        return true
    }

    override fun getItemViewBinding(context: Context, parent: ViewGroup): ViewBinding {
        return ItemContentSingleBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun isItemView(entity: Content, position: Int): Boolean {
        return entity.images.size == 1
    }

    override fun convert(holder: ViewBindingHolder, entity: Content, position: Int) {
        holder.getViewBinding<ItemContentSingleBinding>().run {
            holder.itemView.setBackgroundColorCompat(R.color.colorThemeItem)
            tvAuthor.setTextColorCompat(R.color.colorPrimaryDark)
            tvCategory.setTextColorCompat(R.color.colorPrimary)
            tvTitle.setTextColorCompat(R.color.colorAccent)
            tvDescription.setTextColorCompat(R.color.colorPrimaryDark)
            tvLike.setTextColorCompat(R.color.colorPrimary)
            tvView.setTextColorCompat(R.color.colorPrimary)
            tvTime.setTextColorCompat(R.color.colorPrimaryDark)

            entity.run {
                //设置图片
                GlideTools.get().loadImage(
                    HttpConstant.getCropImagePath(images[0]),
                    ivPicture
                )

                //设置文本
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


    override fun viewRecycled(holder: ViewBindingHolder) {
        holder.getViewBinding<ItemContentSingleBinding>().run {
            ivPicture.run {
                Glide.with(this.context).clear(this)
            }
        }
    }

}