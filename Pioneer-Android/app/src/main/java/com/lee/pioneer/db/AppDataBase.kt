package com.lee.pioneer.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lee.library.base.BaseApplication
import com.lee.pioneer.db.dao.ContentHistoryDao
import com.lee.pioneer.model.entity.ContentHistory

/**
 * @author jv.lee
 * @date 2020/4/16
 * @description 数据库
 */
@Database(entities = [ContentHistory::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contentHistoryDao(): ContentHistoryDao

    companion object {
        private const val DBName = "pioneer-database.db"

        @Volatile
        private var dataBase: AppDataBase? = null

        @JvmStatic
        fun get() = dataBase ?: synchronized(this) {
            dataBase ?: Room.databaseBuilder(
                BaseApplication.getContext(),
                AppDataBase::class.java,
                DBName
            ).build().also { dataBase = it }
        }

    }

}