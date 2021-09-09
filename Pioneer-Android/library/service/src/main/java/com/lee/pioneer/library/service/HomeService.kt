package com.lee.pioneer.library.service

import com.lee.library.adapter.base.BaseViewItem

/**
 * @author jv.lee
 * @data 2021/9/9
 * @description
 */
interface HomeService {
    fun getContentMultipleItem(): BaseViewItem<*>
    fun getContentSingleItem(): BaseViewItem<*>
    fun getContentTextItem(): BaseViewItem<*>

}