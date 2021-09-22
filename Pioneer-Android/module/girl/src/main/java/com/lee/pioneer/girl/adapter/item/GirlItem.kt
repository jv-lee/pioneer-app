package com.lee.pioneer.girl.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.lee.library.adapter.binding.ViewBindingHolder
import com.lee.library.adapter.item.ViewBindingItem
import com.lee.pioneer.girl.R
import com.lee.pioneer.girl.databinding.ItemGirlBinding
import com.lee.pioneer.library.common.constant.HttpConstant
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.tools.GlideTools

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlItem : ViewBindingItem<Content>() {

    override fun getItemViewBinding(context: Context, parent: ViewGroup): ViewBinding {
        return ItemGirlBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun openRecycler(): Boolean {
        return true
    }

    override fun isItemView(entity: Content, position: Int): Boolean {
        return entity.viewType == 0
    }

    override fun convert(holder: ViewBindingHolder, entity: Content, position: Int) {
        holder.getViewBinding<ItemGirlBinding>().run {
            entity.images.takeIf { it.isNotEmpty() }?.get(0)?.let {
                GlideTools.get().loadPlaceholderImage(
                    HttpConstant.getCropImagePath(it),
                    R.drawable.shape_theme_placeholder,
                    ivPicture
                )
            }

            tvDescription.text = entity.desc
            tvDescription.setTextColor(
                ContextCompat.getColor(
                    holder.convertView?.context!!,
                    R.color.colorThemePrimary
                )
            )
            shadowContainer.setShadowFillColor(
                ContextCompat.getColor(
                    holder.convertView?.context!!,
                    R.color.colorThemeItem
                )
            )
        }
    }

    override fun viewRecycled(holder: ViewBindingHolder, entity: Content, position: Int) {
        holder.getViewBinding<ItemGirlBinding>().run {
            ivPicture.run {
                Glide.with(this.context).clear(this)
            }
        }
    }

}