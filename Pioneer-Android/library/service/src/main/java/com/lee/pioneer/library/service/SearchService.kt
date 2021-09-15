package com.lee.pioneer.library.service

import androidx.navigation.NavController
import com.lee.pioneer.library.service.core.IModuleService

/**
 * @author jv.lee
 * @data 2021/9/15
 * @description
 */
interface SearchService : IModuleService {
    fun navigationSearch(navController: NavController)
}