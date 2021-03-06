package com.lee.pioneer.tools

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.lee.library.widget.WebViewEx

/**
 * @author jv.lee
 * @date 2020/4/8
 * @description 全局单例webView
 */
class WebViewTools constructor(context: Context) {

    private var web: WebViewEx? = null

    init {
        WebViewEx(context).also {
            web = it
            web?.run {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                loadEmpty()
            }
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

    fun onDestroy() {
        web = null
        instance = null
    }

}