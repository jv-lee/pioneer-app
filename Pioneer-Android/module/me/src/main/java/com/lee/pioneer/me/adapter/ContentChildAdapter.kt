package com.lee.pioneer.me.adapter

import android.content.Context
import com.lee.library.adapter.base.BaseViewAdapter
import com.lee.pioneer.me.adapter.item.ContentChildItem
import com.lee.pioneer.model.entity.ContentHistory

/**
 * @author jv.lee
 * @date 2020/4/23
 * @description
 */
class ContentChildAdapter(context: Context, data: ArrayList<ContentHistory>) :
    BaseViewAdapter<ContentHistory>(context, data) {
    init {
        addItemStyles(ContentChildItem())
    }
}