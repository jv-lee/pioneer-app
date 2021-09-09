package com.lee.pioneer.home

import com.google.auto.service.AutoService
import com.lee.library.adapter.base.BaseViewItem
import com.lee.pioneer.home.view.adapter.item.ContentMultipleItem
import com.lee.pioneer.home.view.adapter.item.ContentSingleItem
import com.lee.pioneer.home.view.adapter.item.ContentTextItem
import com.lee.pioneer.library.service.HomeService

/**
 * @author jv.lee
 * @data 2021/9/9
 * @description
 */
@AutoService(HomeService::class)
class HomeServiceImpl : HomeService {

    override fun getContentMultipleItem(): BaseViewItem<*> {
        return ContentMultipleItem()
    }

    override fun getContentSingleItem(): BaseViewItem<*> {
        return ContentSingleItem()
    }

    override fun getContentTextItem(): BaseViewItem<*> {
        return ContentTextItem()
    }

}