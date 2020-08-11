package com.lee.pioneer.view.adapter

import android.content.Context
import com.lee.library.adapter.LeeViewAdapter
import com.lee.library.adapter.page.PagingAdapter
import com.lee.pioneer.model.entity.ContentHistory
import com.lee.pioneer.view.adapter.item.ContentChildItem

/**
 * @author jv.lee
 * @date 2020/4/23
 * @description
 */
class ContentChildAdapter(context: Context, data: ArrayList<ContentHistory>) :
    PagingAdapter<ContentHistory>(context, data) {
    init {
        addItemStyles(ContentChildItem())
    }
}