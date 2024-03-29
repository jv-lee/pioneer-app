package com.lee.pioneer.recommend.view.adapter

import android.content.Context
import com.lee.library.adapter.binding.ViewBindingAdapter
import com.lee.library.adapter.item.ViewBindingItem
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.service.HomeService
import com.lee.pioneer.library.service.hepler.ModuleService

/**
 * @author jv.lee
 * @date 2020/3/27
 * @description 内容列表适配器
 */
class ContentAdapter(context: Context, data: ArrayList<Content>) :
    ViewBindingAdapter<Content>(context, data) {

    init {
        ModuleService.find<HomeService>().run {
            addItemStyles(getContentMultipleItem() as ViewBindingItem<Content>)
            addItemStyles(getContentSingleItem() as ViewBindingItem<Content>)
            addItemStyles(getContentTextItem() as ViewBindingItem<Content>)
        }
    }

}