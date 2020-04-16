package com.lee.pioneer.model.entity

import androidx.annotation.IntDef
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lee.pioneer.model.entity.HistoryType.Companion.CONTENT
import com.lee.pioneer.model.entity.HistoryType.Companion.PICTURE

/**
 * @author jv.lee
 * @date 2020/4/16
 * @description 数据库操作类
 */
@Target(
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.PROPERTY
)
@IntDef(CONTENT, PICTURE)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class HistoryType {
    companion object {
        const val CONTENT = 0
        const val PICTURE = 1
    }
}

@Target(
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.PROPERTY
)
@IntDef(CONTENT, PICTURE)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class HistorySource {
    companion object {
        const val ID = 0
        const val URL = 1
    }
}

/**
 * 浏览记录
 * @param id 自增键
 * @param type 文字类型/妹子图类型
 * @param source id/url
 */
@Entity
data class ContentHistory(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @HistoryType val type: Int,
    @HistorySource val source: Int,
    @Embedded val content: Content
)