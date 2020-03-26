package com.lee.pioneer.http

import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.model.entity.Category
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.Data
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description
 */
interface ApiService {

    /**
     * TODO 首页banner轮播
     */
    @GET("banners")
    fun getBannerAsync(): Deferred<Data<Banner>>

    /**
     * TODO 分类 API
     * @param categoryType  可接受参数 Article | GanHuo | Girl
     * Article： 专题分类
     * GanHuo： 干货分类
     * Girl：妹子图
     */
    @GET("categories/{category_type}")
    fun getCategoriesAsync(@Path("category_type") categoryType: String): Deferred<Data<Category>>

    /**
     * TODO 分类数据 API
     * @param category 可接受参数 All(所有分类) | Article | GanHuo | Girl
     * @param type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
     * @param page: >=1
     * @param count： [10, 50]
     */
    @GET("data/category/{category}/type/{type}/page/{page}/count/{count}")
    fun getCategoryDataAsync(
        @Path("category") category: String,
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("count") count: Int
    ): Deferred<Data<Content>>

    /**
     * TODO 随机数据API
     * @param category 可接受参数 Article | GanHuo | Girl
     * @param type 可接受参数 Android | iOS | Flutter | Girl，即分类API返回的类型数据
     * @param count： [1, 50]
     */
    @GET("random/category/{category}/type/{type}/count/{count}")
    fun getRandomDataAsync(
        @Path("category") category: String,
        @Path("type") type: String,
        @Path("count") count: Int
    ): Deferred<Any>

    /**
     * TODO 本周最热 API
     * @param hotType 可接受参数 views（浏览数） | likes（点赞数） | comments（评论数）❌
     * @param category 可接受参数 Article | GanHuo | Girl
     * @param count： [1, 20]
     */
    @GET("hot/{hot_type}/category/{category}/count/{count}")
    fun getHotDataAsync(
        @Path("hot_type") hotType: String,
        @Path("category") category: String,
        @Path("count") count: Int
    ): Deferred<Data<Content>>

    /**
     * TODO 文章评论获取 API
     * @param postId 可接受参数 文章Id
     */
    @GET("post/comments/{post_id}")
    fun getCommentsAsync(@Path("post_id") postId: String): Deferred<Any>

    /**
     * TODO 搜索 API
     * @param search 可接受参数 要搜索的内容
     * @param category 可接受参数 All[所有分类] | Article | GanHuo
     * @param type 可接受参数 Android | iOS | Flutter ...，即分类API返回的类型数据
     * @param count： [10, 50]
     * @param page: >=1
     */
    @GET("search/{search}/category/{category}/type/{type}/page/{page}/count/{count}")
    fun getSearchDataAsync(
        @Path("search") search: String,
        @Path("category") category: String,
        @Path("type") type: String,
        @Path("page") page: Int,
        @Path("count") count: Int
    ): Deferred<Data<Content>>

}