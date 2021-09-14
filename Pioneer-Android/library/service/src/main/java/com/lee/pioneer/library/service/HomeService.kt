package com.lee.pioneer.library.service

import com.lee.library.adapter.base.BaseViewItem
import com.lee.pioneer.library.service.core.IModuleService

/**
 * @author jv.lee
 * @data 2021/9/9
 * @description
 */
interface HomeService : IModuleService {
    fun getContentMultipleItem(): BaseViewItem<*>
    fun getContentSingleItem(): BaseViewItem<*>
    fun getContentTextItem(): BaseViewItem<*>

}