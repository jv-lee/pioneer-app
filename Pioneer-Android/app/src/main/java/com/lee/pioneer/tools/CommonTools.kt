package com.lee.pioneer.tools

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
    }

}