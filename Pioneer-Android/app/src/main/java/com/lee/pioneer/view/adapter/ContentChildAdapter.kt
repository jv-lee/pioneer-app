package com.lee.pioneer.view.adapter

import android.content.Context
import com.lee.library.adapter.base.BaseViewAdapter
import com.lee.pioneer.model.entity.ContentHistory
import com.lee.pioneer.view.adapter.item.ContentChildItem

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