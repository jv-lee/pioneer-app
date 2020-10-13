package com.lee.pioneer.model.repository

import com.lee.library.mvvm.base.BaseRepository
import com.lee.library.net.HttpManager
import com.lee.library.net.request.IRequest
import com.lee.library.net.request.Request
import com.lee.pioneer.constants.HttpConstant
import com.lee.pioneer.http.ApiService
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.model.entity.PageData

/**
 * @author jv.lee
 * @date 2020/10/13
 * @description
 */
class TestRepository : BaseRepository() {

    private val api: ApiService

    init {
        val request = Request(HttpConstant.BASE_URI, IRequest.ConverterType.JSON)
        api = HttpManager.getInstance().getService(ApiService::class.java, request)
    }

    suspend fun getBanner(): PageData<Banner> {
        return apiCall { api.getResponseDataBannerAsync() }
    }

    suspend fun getBanner2(): PageData<Banner> {
        return api.getPageDataBannerAsync()
    }

}