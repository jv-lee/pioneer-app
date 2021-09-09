package com.lee.pioneer.girl.model.api

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
     *  分类数据 API
     * @param category 可接受参数 All(所有分类) | Article | GanHuo | Girl
     * @param type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
     * @param page: >=1
     * @param count： [10, 50]
     */
    @GET("data/category/{category}/type/{type}/page/{page}/count/{count}")
    suspend fun getContentDataAsync(
        @Path("category") category: String,
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("count") count: Int
    ): PageData<Content>

}