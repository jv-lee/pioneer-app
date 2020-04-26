package com.lee.pioneer.tools

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

        fun setNightMode(): Boolean {
            return if (PreferencesTools.hasNightMode()) {
                PreferencesTools.setNightModel(false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                GlideTools.get().updatePlaceholder()
                false
            } else {
                PreferencesTools.setNightModel(true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                GlideTools.get().updatePlaceholder()
                true
            }
        }

        fun hasNightMode() {
            if (PreferencesTools.hasNightMode()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}