package com.lee.pioneer.http

import com.lee.library.net.HttpManager
import com.lee.library.net.request.IRequest
import com.lee.library.net.request.Request
import com.lee.pioneer.const.HttpConstant
import kotlinx.coroutines.Deferred

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description api接口实现类 全局单例
 */
class ApiImpl : ApiInterface {

    private val api: ApiInterface

    init {
        val request = Request(HttpConstant.BASE_URI, IRequest.ConverterType.JSON)
        api = HttpManager.getInstance().getService(ApiInterface::class.java, request)
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

    override fun getBannerAsync(): Deferred<Any> {
        return api.getBannerAsync()
    }

    override fun getCategoriesAsync(categoryType: String): Deferred<Any> {
        return api.getCategoriesAsync(categoryType)
    }

    override fun getCategoryDataAsync(
        category: String,
        type: String,
        page: Int,
        count: Int
    ): Deferred<Any> {
        return api.getCategoryDataAsync(category, type, page, count)
    }

}