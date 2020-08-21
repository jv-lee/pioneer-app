package com.lee.pioneer.view.recommend

import android.content.Context
import com.lee.library.adapter.page.PagingAdapter

/**
 * @author jv.lee
 * @date 2020/8/21
 * @description
 */
class RecommendAdapter(context: Context, data: ArrayList<Recommend>) :
    PagingAdapter<Recommend>(context, data) {
    init {
        addItemStyles(BannerItem())
        addItemStyles(IconItem())
        addItemStyles(CommonItem())
        addItemStyles(RisingItem())
        addItemStyles(TagItem())
    }
}