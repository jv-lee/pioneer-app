package com.lee.pioneer.tools

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.lee.library.widget.WebViewEx
import kotlin.Exception

/**
 * @author jv.lee
 * @date 2020/4/8
 * @description
 */
class WebViewTools constructor(context: Context) {

    private val web: WebViewEx

    init {
        WebViewEx(context).also {
            web = it
            web.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }
    }

    companion object {
        @Volatile
        private var instance: WebViewTools? = null

        fun get(context: Context) = instance ?: synchronized(WebViewTools::class.java) {
            instance ?: WebViewTools(context).also { instance = it }
        }

        fun get() = instance ?: throw Exception("请先调用WebViewTools.get(application)初始化")

        fun getWeb() = get().web
    }

}