package com.lee.pioneer.search

import androidx.core.net.toUri
import androidx.navigation.NavController
import com.google.auto.service.AutoService
import com.lee.library.base.BaseApplication
import com.lee.pioneer.library.service.SearchService
import com.lee.pioneer.router.NavigationAnim
import com.lee.pioneer.router.navigationDeepLink

/**
 * @author jv.lee
 * @data 2021/9/15
 * @description
 */
@AutoService(SearchService::class)
class SearchServiceImpl : SearchService {
    override fun navigationSearch(navController: NavController) {
        navController.navigationDeepLink(
            BaseApplication.getContext().getString(R.string.module_deep_link_search).toUri(),
            NavigationAnim.Bottom
        )
    }
}