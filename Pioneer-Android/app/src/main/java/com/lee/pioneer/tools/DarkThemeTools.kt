package com.lee.pioneer.tools

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate

/**
 * @author jv.lee
 * @date 2020/6/9
 * @description 深色主题适配方法
 */
class DarkThemeTools {

    companion object {

        /**
         * 当前是否为系统主题
         */
        fun isSystemTheme(context: Context): Boolean {
            return AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED || AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }

        /**
         * 当前是否为深色主题
         */
        fun isDarkTheme(context: Context): Boolean {
            val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            return flag == Configuration.UI_MODE_NIGHT_YES
        }

        /**
         * 设置为跟随系统主题变更
         */
        fun updateSystemTheme(enable: Boolean, context: Context) {
            if (enable) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                return
            }
            if (isDarkTheme(context)) {
                updateNightTheme(true)
            } else {
                updateNightTheme(false)
            }
        }

        /**
         * 设置深色主题
         */
        fun updateNightTheme(enable: Boolean) {
            if (enable) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }

    }

}