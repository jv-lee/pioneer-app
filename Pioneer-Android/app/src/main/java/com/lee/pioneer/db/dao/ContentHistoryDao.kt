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

    /**
     * 查询所有浏览记录
     */
    @Query("SELECT * FROM ContentHistory ORDER BY read_time DESC")
    fun queryContentHistory(): List<ContentHistory>

    /**
     * 查询所有收藏的内容
     */
    @Query("SELECT * FROM ContentHistory WHERE is_favorite =1")
    fun queryContentFavorite(): List<ContentHistory>

    /**
     * 查询该条记录是否点击收藏
     */
    @Query("SELECT COUNT(*) FROM ContentHistory WHERE _id = :id AND is_favorite = 1")
    fun isFavorite(id: String): Int

    /**
     * 通过id 查询出一条内容
     */
    @Query("SELECT * FROM ContentHistory WHERE _id = :id")
    fun queryContentById(id: String): List<ContentHistory>

}