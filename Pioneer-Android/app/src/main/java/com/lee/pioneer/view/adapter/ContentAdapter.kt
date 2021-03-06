package com.lee.pioneer.view.adapter

import android.content.Context
import com.lee.library.adapter.LeeViewAdapter
import com.lee.library.adapter.page.PagingAdapter
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.view.adapter.item.ContentMultipleItem
import com.lee.pioneer.view.adapter.item.ContentSingleItem
import com.lee.pioneer.view.adapter.item.ContentTextItem

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容列表适配器
 */
class ContentAdapter(context: Context, data: ArrayList<Content>) :
    PagingAdapter<Content>(context, data) {

    init {
        addItemStyles(ContentSingleItem())
        addItemStyles(ContentMultipleItem())
        addItemStyles(ContentTextItem())
    }

}