package com.lee.pioneer.db.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * @author jv.lee
 * @date 2020/4/16
 * @description
 */
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Delete
    fun delete(vararg obj: T)

}