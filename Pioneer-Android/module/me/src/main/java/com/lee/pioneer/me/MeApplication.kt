package com.lee.pioneer.me

import android.app.Application
import com.google.auto.service.AutoService
import com.lee.pioneer.library.service.ApplicationService
import com.lee.pioneer.me.db.AppDataBase

/**
 * @author jv.lee
 * @date 2021/9/9
 * @description
 */
@AutoService(ApplicationService::class)
class MeApplication : ApplicationService {
    override fun init(application: Application) {
        AppDataBase.getInstance(application)
    }
}