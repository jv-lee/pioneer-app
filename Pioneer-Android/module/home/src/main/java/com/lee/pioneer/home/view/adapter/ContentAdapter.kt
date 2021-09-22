package com.lee.pioneer.home.view.adapter

import android.content.Context
import com.lee.library.adapter.base.BaseViewAdapter
import com.lee.library.adapter.binding.ViewBindingAdapter
import com.lee.pioneer.home.view.adapter.item.ContentMultipleItem
import com.lee.pioneer.home.view.adapter.item.ContentSingleItem
import com.lee.pioneer.home.view.adapter.item.ContentTextItem
import com.lee.pioneer.library.common.entity.Content

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容列表适配器
 */
class ContentAdapter(context: Context, data: ArrayList<Content>) :
    ViewBindingAdapter<Content>(context, data) {

    init {
        addItemStyles(ContentSingleItem())
        addItemStyles(ContentMultipleItem())
        addItemStyles(ContentTextItem())
    }

}