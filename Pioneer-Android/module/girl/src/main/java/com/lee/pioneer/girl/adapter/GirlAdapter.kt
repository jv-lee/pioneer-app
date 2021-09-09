package com.lee.pioneer.girl.adapter

import android.content.Context
import com.lee.library.adapter.base.BaseViewAdapter
import com.lee.pioneer.girl.adapter.item.GirlItem
import com.lee.pioneer.library.common.entity.Content

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