package com.lee.pioneer.library.service.hepler

import android.app.Application
import com.lee.pioneer.library.service.ApplicationService
import java.util.*

/**
 * @author jv.lee
 * @date 2021/9/9
 * @description
 */
object ApplicationService {
    fun init(application: Application) {
        val iterator = ServiceLoader.load(ApplicationService::class.java).iterator()
        while (iterator.hasNext()) {
            iterator.next().init(application)
        }
    }
}