package com.lee.pioneer.constants

import com.lee.pioneer.BuildConfig

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 服务端常量
 */
interface HttpConstant {
    companion object {
        const val BASE_URI = BuildConfig.BASE_URI
        const val BASE_HTTP_SUCCESS = 100

        fun getDetailsUri(id: String) = "https://gank.io/post/$id"
        fun getNoneHeaderJs() =
            "javascript:(function(){document.getElementsByClassName('header')[0].style.display = 'none'})()"
    }
}