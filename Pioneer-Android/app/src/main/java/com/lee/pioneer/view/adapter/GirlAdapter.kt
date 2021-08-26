package com.lee.pioneer.view.adapter

import android.content.Context
import com.lee.library.adapter.base.BaseViewAdapter
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.view.adapter.item.GirlItem

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlAdapter(context: Context, data: ArrayList<Content>) :
    BaseViewAdapter<Content>(context, data) {

    init {
        addItemStyles(GirlItem())
    }

}