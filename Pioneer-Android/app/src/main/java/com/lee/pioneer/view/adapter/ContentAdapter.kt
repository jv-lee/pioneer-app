package com.lee.pioneer.view.adapter

import android.content.Context
import com.lee.library.adapter.LeeViewAdapter
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.view.item.ContentSignItem

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description
 */
class ContentAdapter(context: Context, data: ArrayList<Content>) :
    LeeViewAdapter<Content>(context, data) {

    init {
        addItemStyles(ContentSignItem())
    }

}