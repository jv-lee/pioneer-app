package com.lee.pioneer.tools

import com.lee.library.utils.SPUtil

/**
 * @author jv.lee
 * @date 2020/4/24
 * @description
 */
class PreferencesTools {

    companion object {
        private const val NIGHT_MODE = "night_mode"

        fun hasNightMode(): Boolean {
            return SPUtil.get(NIGHT_MODE, false) as Boolean
        }

        fun setNightModel(mode: Boolean) {
            SPUtil.save(NIGHT_MODE, mode)
        }
    }
}