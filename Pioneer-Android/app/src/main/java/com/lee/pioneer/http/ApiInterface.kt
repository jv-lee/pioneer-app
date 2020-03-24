package com.lee.pioneer.http

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description
 */
interface ApiInterface {

    /**
     * TODO 首页banner轮播
     */
    @GET("/banner")
    fun getBannerAsync(): Deferred<Any>

    /**
     * TODO 分类 API
     * @param categoryType  可接受参数 Article | GanHuo | Girl
     * Article： 专题分类
     * GanHuo： 干货分类
     * Girl：妹子图
     */
    @GET("/categories/{category_type}")
    fun getCategoriesAsync(@Path("category_type") categoryType: String): Deferred<Any>

    /**
     * TODO 分类数据 API
     * @param category 可接受参数 All(所有分类) | Article | GanHuo | Girl
     * @param type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
     * @param page: >=1
     * @param count： [10, 50]
     */
    @GET("/data/category/{category}}/type/{type}}/page/{page}}/count/{count}}")
    fun getCategoryDataAsync(
        @Path("category") category: String,
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("count") count: Int
    ): Deferred<Any>




}