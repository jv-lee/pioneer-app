package com.lee.pioneer.view.recommend

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author jv.lee
 * @date 2020/8/5
 * @description
 */
data class Recommend(
    val title: String,
    val type: ViewStyle,
    val banners: ArrayList<Banner> = ArrayList(),
    val tags: ArrayList<Tag> = ArrayList(),
    val comics: ArrayList<Comic> = ArrayList()
)  {

}

@Parcelize
data class Banner(
    val bannerType: Int,
    val url: String,
    val title: String,
    val appNavigation: AppNavigation? = null
) : Parcelable

@Parcelize
data class Tag(
    val id: String,
    val text: String,
    var isSelected: Boolean = false
) : Parcelable {
}

@Parcelize
data class Category(val id: String, val name: String, var isSelected: Boolean = false) :
    Parcelable

@Parcelize
data class Chapter(
    val id: String,
    val title: String,
    val price: Int,
    val isUnlock: Boolean,
    val volumeId: String,
    val updateTime: String
) : Parcelable

@Parcelize
data class Comic(
    val id: String,
    val name: String,
    val desc: String,
    val author: String,
    val chapterCount: Int,
    val cover: String,
    val category: Category,
    val tags: ArrayList<Tag>,
    val updateTime: String,
    val status: Int,
    val chapter: ArrayList<Chapter>,
    val rising: Int
) : Parcelable

@Parcelize
data class AppNavigation(
    val path: String,
    val params: String
) : Parcelable

enum class ViewStyle {
    BANNER,
    ICON,
    COMMON,
    RISING,
    TAG
}