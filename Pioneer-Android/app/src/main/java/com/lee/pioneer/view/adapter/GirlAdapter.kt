package com.lee.pioneer.view.adapter

import android.content.Context
import com.lee.library.adapter.LeeViewAdapter
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.view.adapter.item.GirlBottomItem
import com.lee.pioneer.view.adapter.item.GirlTopItem

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlAdapter(context: Context, data: ArrayList<Content>) :
    LeeViewAdapter<Content>(context, data) {

    init {
        addItemStyles(GirlTopItem())
        addItemStyles(GirlBottomItem())
    }

}