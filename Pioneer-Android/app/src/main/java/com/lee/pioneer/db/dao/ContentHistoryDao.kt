package com.lee.pioneer.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.lee.pioneer.db.base.BaseDao
import com.lee.pioneer.model.entity.ContentHistory
import kotlinx.coroutines.Deferred

/**
 * @author jv.lee
 * @date 2020/4/16
 * @description
 */
@Dao
interface ContentHistoryDao : BaseDao<ContentHistory> {
    @Query("SELECT * FROM ContentHistory")
    fun queryContentHistoryAsync(): Deferred<List<ContentHistory>>

}