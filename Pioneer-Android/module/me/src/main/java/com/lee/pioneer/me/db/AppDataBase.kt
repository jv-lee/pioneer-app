package com.lee.pioneer.me.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lee.pioneer.library.common.entity.ContentHistory
import com.lee.pioneer.me.db.dao.ContentHistoryDao

/**
 * @author jv.lee
 * @date 2020/4/16
 * @description 数据库
 */
@Database(entities = [ContentHistory::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contentHistoryDao(): ContentHistoryDao

    companion object {
        private const val DBName = "pioneer-database.db"

        @Volatile
        private var instance: AppDataBase? = null

        @JvmStatic
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                DBName
            )
                .allowMainThreadQueries()
                .build().also { instance = it }
        }

        fun get() = instance ?: throw Exception("请先调用AppDataBase.getInstance(context) 初始化.")

    }

}