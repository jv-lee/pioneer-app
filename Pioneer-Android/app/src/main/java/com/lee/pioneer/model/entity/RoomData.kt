package com.lee.pioneer.model.entity

import androidx.annotation.IntDef
import androidx.room.*
import com.lee.pioneer.db.converters.StringListConverter
import com.lee.pioneer.model.entity.HistoryType.Companion.CONTENT
import com.lee.pioneer.model.entity.HistoryType.Companion.PICTURE

/**
 * @author jv.lee
 * @date 2020/4/16
 * @description 数据库操作类
 */
@Target(
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER
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
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER
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
@TypeConverters(StringListConverter::class)
data class ContentHistory(
    @ColumnInfo(name = "history_id") @PrimaryKey(autoGenerate = false) var id: String,
    @ColumnInfo(name = "history_type") @HistoryType val type: Int,
    @ColumnInfo(name = "history_source") @HistorySource val source: Int,
    @ColumnInfo(name = "read_time") val readTime: Long,
    @Embedded val content: Content
) {
    companion object {
        fun push(@HistoryType type: Int, @HistorySource source: Int, content: Content) =
            ContentHistory(content._id, type, source, System.currentTimeMillis(), content)
    }
}