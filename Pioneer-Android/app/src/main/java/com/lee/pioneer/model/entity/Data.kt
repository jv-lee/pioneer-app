package com.lee.pioneer.model.entity

/**
 * @author jv.lee
 * @date 2020/3/25
 * @description
 */
data class Data<T>(
    val data: T,
    val status: Int,
    val count: Int,
    val hot: String,
    val category: String,
    val page: Int,
    val page_count: Int,
    val total_counts: Int
)

/**
 * BannerAPI data<T>
 */
data class Banner(
    val image: String,
    val title: String,
    val url: String
)

/**
 *  分类数据 data<T>
 */
data class Category(
    val _id: String,
    val coverImageUrl: String,
    val desc: String,
    val title: String,
    val type: String
)

/**
 * 分页具体数据列表 data<T>
 * 最热数据列表 data<T>
 * 搜索数据列表 data<T>
 */
data class Content(
    val _id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,
    val images: List<String>,
    val likeCounts: Int,
    val publishedAt: String,
    val stars: Int,
    val title: String,
    val type: String,
    val url: String,
    val views: Int,
    var viewType: Int = 0
)

data class Details(
    val _id: String,
    val author: String,
    val category: String,
    val content: String,
    val createdAt: String,
    val desc: String,
    val email: String,
    val images: List<String>,
    val index: Int,
    val isOriginal: Boolean,
    val license: String,
    val likeCount: Int,
    val likeCounts: Int,
    val likes: List<Any>,
    val markdown: String,
    val originalAuthor: String,
    val publishedAt: String,
    val stars: Int,
    val status: Int,
    val tags: List<String>,
    val title: String,
    val type: String,
    val updatedAt: String,
    val url: String,
    val views: Int
)