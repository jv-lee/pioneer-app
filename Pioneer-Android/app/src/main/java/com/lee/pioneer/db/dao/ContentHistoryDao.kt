package com.lee.pioneer.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.lee.pioneer.db.base.BaseDao
import com.lee.pioneer.model.entity.ContentHistory

/**
 * @author jv.lee
 * @date 2020/4/16
 * @description
 */
@Dao
interface ContentHistoryDao : BaseDao<ContentHistory> {
    @Query("SELECT * FROM ContentHistory ORDER BY read_time DESC")
    fun queryContentHistoryAsync(): List<ContentHistory>

}