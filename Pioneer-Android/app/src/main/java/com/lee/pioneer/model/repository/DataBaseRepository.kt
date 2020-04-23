package com.lee.pioneer.model.repository

import com.lee.pioneer.db.AppDataBase

/**
 * @author jv.lee
 * @date 2020/4/22
 * @description
 */
class DataBaseRepository {

    val historyDao by lazy { AppDataBase.get().contentHistoryDao() }

    companion object {

        @Volatile
        private var instance: DataBaseRepository? = null

        @JvmStatic
        fun get() = instance ?: synchronized(this) {
            instance ?: DataBaseRepository().also { instance = it }
        }

    }



}