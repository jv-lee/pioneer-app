package com.lee.pioneer.http

import com.lee.library.net.HttpManager
import com.lee.library.net.request.IRequest
import com.lee.library.net.request.Request
import com.lee.pioneer.const.HttpConstant
import com.lee.pioneer.http.service.ApiService
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.model.entity.Category
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.Data
import kotlinx.coroutines.Deferred

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description api接口实现类 全局单例
 */
class ApiImpl : ApiService {

    private val api: ApiService

    init {
        val request =
            Request(HttpConstant.BASE_URI, IRequest.ConverterType.JSON)
        api = HttpManager.getInstance().getService(ApiService::class.java, request)
    }

    companion object {
        @Volatile
        private var instance: ApiImpl? = null

        @JvmStatic
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ApiImpl().also { instance = it }
            }
    }

    override fun getBannerAsync(): Deferred<Data<Banner>> {
        return api.getBannerAsync()
    }

    override fun getCategoriesAsync(categoryType: String): Deferred<Data<Category>> {
        return api.getCategoriesAsync(categoryType)
    }

    override fun getCategoryDataAsync(
        category: String,
        type: String,
        page: Int,
        count: Int
    ): Deferred<Data<Content>> {
        return api.getCategoryDataAsync(category, type, page, count)
    }

    override fun getRandomDataAsync(category: String, type: String, count: Int): Deferred<Any> {
        return api.getRandomDataAsync(category, type, count)
    }

    override fun getHotDataAsync(
        hotType: String,
        category: String,
        count: Int
    ): Deferred<Data<Content>> {
        return api.getHotDataAsync(hotType, category, count)
    }

    override fun getCommentsAsync(postId: String): Deferred<Any> {
        return api.getCommentsAsync(postId)
    }

    override fun getSearchDataAsync(
        search: String,
        category: String,
        type: String,
        page: Int,
        count: Int
    ): Deferred<Data<Content>> {
        return api.getSearchDataAsync(search, category, type, page, count)
    }

}