package com.lee.pioneer.me.adapter

import android.content.Context
import com.lee.library.adapter.binding.ViewBindingAdapter
import com.lee.pioneer.library.common.entity.ContentHistory
import com.lee.pioneer.me.adapter.item.ContentChildItem

/**
 * @author jv.lee
 * @date 2020/4/23
 * @description
 */
class ContentChildAdapter(context: Context, data: ArrayList<ContentHistory>) :
    ViewBindingAdapter<ContentHistory>(context, data) {

    init {
        addItemStyles(ContentChildItem())
    }

}