package com.lee.pioneer.tools

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

/**
 * @author jv.lee
 * @date 2020/3/26
 * @description 项目通用方法类
 */
class CommonTools {

    companion object {
        /**
         * 数据总数转总页数
         */
        fun totalToPage(totalCount: Int, pageSize: Int): Int {
            return if (totalCount % pageSize != 0) {
                totalCount / pageSize + 1
            } else {
                totalCount / pageSize
            }
        }

        /**
         * 当前是否为深色主题
         */
        fun isDarkTheme(context: Context): Boolean {
            val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            return flag == Configuration.UI_MODE_NIGHT_YES
        }

        /**
         * 动态更改深色主题
         */
        fun updateDarkTheme(context: Context) {
            if (isDarkTheme(context)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        /**
         * 设置为跟随系统主题变更
         */
        fun updateSystemTheme(context: Context) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }

    }

}