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

        fun loadReady() =
            "javascript:(function () {\n" +
                    "   var ie = !!(window.attachEvent && !window.opera);\n" +
                    "   var wk = /webkit\\/(\\d+)/i.test(navigator.userAgent) && (RegExp.\$1 < 525);\n" +
                    "   var fn = [];\n" +
                    "   var run = function () { for (var i = 0; i < fn.length; i++) fn[i](); };\n" +
                    "   var d = document;\n" +
                    "   d.ready = function (f) {\n" +
                    "      if (!ie && !wk && d.addEventListener)\n" +
                    "      return d.addEventListener('DOMContentLoaded', f, false);\n" +
                    "      if (fn.push(f) > 1) return;\n" +
                    "      if (ie)\n" +
                    "         (function () {\n" +
                    "            try { d.documentElement.doScroll('left'); run(); }\n" +
                    "            catch (err) { setTimeout(arguments.callee, 0); }\n" +
                    "         })();\n" +
                    "      else if (wk)\n" +
                    "      var t = setInterval(function () {\n" +
                    "         if (/^(loaded|complete)\$/.test(d.readyState))\n" +
                    "         clearInterval(t), run();\n" +
                    "      }, 0);\n" +
                    "   };\n" +
                    "})();"

        fun hideHeader() =
            "javascript:(function(){document.ready(function(){\n" +
                    "   document.getElementsByClassName('header')[0].style.display = 'none';\n" +
                    "});})()"
    }
}