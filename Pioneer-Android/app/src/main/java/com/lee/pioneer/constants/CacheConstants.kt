package com.lee.pioneer.constants

/**
 * @author jv.lee
 * @date 2020/4/15
 * @description
 */
interface CacheConstants {
    companion object {
        const val CONTENT_CACHE_KEY = "content-cache-key"//首页 contentList数据列表第一页缓存
        const val CATEGORY_CACHE_KEY = "category-cache-key"//首页 分类tab缓存
    }
}