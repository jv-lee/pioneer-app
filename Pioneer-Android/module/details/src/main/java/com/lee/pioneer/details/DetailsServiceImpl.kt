package com.lee.pioneer.details

import androidx.core.net.toUri
import androidx.navigation.NavController
import com.google.auto.service.AutoService
import com.lee.library.base.BaseApplication
import com.lee.pioneer.library.service.DetailsService
import com.lee.pioneer.router.NavigationAnim
import com.lee.pioneer.router.navigationDeepLink

/**
 * @author jv.lee
 * @data 2021/9/15
 * @description
 */
@AutoService(DetailsService::class)
class DetailsServiceImpl : DetailsService {
    override fun navigationDetails(navController: NavController, id: String, url: String) {
        navController.navigationDeepLink(
            "pioneer-app://com.lee.pioneer/details_fragment?id=$id&url=$url".toUri(),
            NavigationAnim.SlideIn
        )
    }
}