package com.lee.pioneer.me

import androidx.annotation.Keep
import com.google.auto.service.AutoService
import com.lee.pioneer.library.common.entity.ContentHistory
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.me.db.AppDataBase

/**
 * @author jv.lee
 * @date 2021/9/9
 * @description
 */
@Keep
@AutoService(MeService::class)
class MeServiceImpl : MeService {
    override fun queryContentHistory(limit: Int): List<ContentHistory> {
        return AppDataBase.get().contentHistoryDao().queryContentHistory(limit)
    }

    override fun queryContentHistoryCount(): Int {
        return AppDataBase.get().contentHistoryDao().queryContentHistoryCount()
    }

    override fun queryContentCollect(limit: Int): List<ContentHistory> {
        return AppDataBase.get().contentHistoryDao().queryContentCollect(limit)
    }

    override fun queryContentCollectCount(): Int {
        return AppDataBase.get().contentHistoryDao().queryContentCollectCount()
    }

    override fun isCollect(id: String): Int {
        return AppDataBase.get().contentHistoryDao().isCollect(id)
    }

    override fun queryContentById(id: String): List<ContentHistory> {
        return AppDataBase.get().contentHistoryDao().queryContentById(id)
    }

    override fun insert(vararg value: ContentHistory) {
        AppDataBase.get().contentHistoryDao().insert(value.toList())
    }

    override fun insert(list: List<ContentHistory>) {
        AppDataBase.get().contentHistoryDao().insert(list)
    }

    override fun delete(vararg value: ContentHistory) {
        AppDataBase.get().contentHistoryDao().delete(value.toList())
    }

    override fun delete(list: List<ContentHistory>) {
        AppDataBase.get().contentHistoryDao().delete(list)
    }

    override fun update(vararg value: ContentHistory) {
        AppDataBase.get().contentHistoryDao().update(value.toList())
    }

    override fun update(list: List<ContentHistory>) {
        AppDataBase.get().contentHistoryDao().update(list)
    }
}