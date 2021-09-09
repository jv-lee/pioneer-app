package com.lee.pioneer.me.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.library.db.base.BaseDao
import com.lee.pioneer.library.common.entity.ContentHistory

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
    @Query("SELECT * FROM ContentHistory ORDER BY read_time DESC LIMIT :limit * ${KeyConstants.PAGE_COUNT},${KeyConstants.PAGE_COUNT}")
    fun queryContentHistory(limit: Int): List<ContentHistory>

    /**
     * 获取浏览记录总条数
     */
    @Query("SELECT COUNT(*) FROM ContentHistory")
    fun queryContentHistoryCount(): Int

    /**
     * 查询所有收藏的内容
     */
    @Query("SELECT * FROM ContentHistory WHERE is_collect = 1 LIMIT :limit * ${KeyConstants.PAGE_COUNT},${KeyConstants.PAGE_COUNT}")
    fun queryContentCollect(limit: Int): List<ContentHistory>

    /**
     * 获取收藏内容总条数
     */
    @Query("SELECT COUNT(*) FROM ContentHistory WHERE is_collect = 1")
    fun queryContentCollectCount(): Int

    /**
     * 查询该条记录是否点击收藏
     */
    @Query("SELECT COUNT(*) FROM ContentHistory WHERE _id = :id AND is_collect = 1")
    fun isCollect(id: String): Int

    /**
     * 通过id 查询出一条内容
     */
    @Query("SELECT * FROM ContentHistory WHERE _id = :id")
    fun queryContentById(id: String): List<ContentHistory>

}