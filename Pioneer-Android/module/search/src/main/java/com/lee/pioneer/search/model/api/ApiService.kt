package com.lee.pioneer.search.model.api

import com.lee.pioneer.library.common.constant.KeyConstants
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.entity.PageData
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author jv.lee
 * @data 2021/9/9
 * @description
 */
interface ApiService {

    /**
     *  搜索 API
     * @param search 可接受参数 要搜索的内容
     * @param category 可接受参数 All[所有分类] | Article | GanHuo
     * @param type 可接受参数 Android | iOS | Flutter ...，即分类API返回的类型数据
     * @param count： [10, 50]
     * @param page: >=1
     */
    @GET("search/{search}/category/{category}/type/{type}/page/{page}/count/{count}")
    suspend fun getSearchDataAsync(
        @Path("search") search: String,
        @Path("page") page: Int,
        @Path("category") category: String = KeyConstants.CATEGORY_ALL,
        @Path("type") type: String = KeyConstants.CATEGORY_ALL,
        @Path("count") count: Int = KeyConstants.PAGE_COUNT
    ): PageData<Content>

}