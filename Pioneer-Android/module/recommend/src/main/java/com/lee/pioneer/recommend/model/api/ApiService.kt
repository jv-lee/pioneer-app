package com.lee.pioneer.recommend.model.api

import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_RECOMMEND
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.library.common.entity.Banner
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.entity.PageData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author jv.lee
 * @data 2021/9/9
 * @description
 */
interface ApiService {

    @GET("banners")
    fun getBannerFlow(): Flow<PageData<Banner>>

    /**
     *  首页banner轮播
     */
    @GET("banners")
    suspend fun getBannerAsync(): PageData<Banner>

    /**
     *  本周最热 API
     * @param hotType 可接受参数 views（浏览数） | likes（点赞数） | comments（评论数）❌
     * @param category 可接受参数 Article | GanHuo | Girl
     * @param count： [1, 20]
     */
    @GET("hot/{hot_type}/category/{category}/count/{count}")
    suspend fun getHotDataAsync(
        @Path("hot_type") hotType: String,
        @Path("category") category: String = CATEGORY_RECOMMEND,
        @Path("count") count: Int = PAGE_COUNT
    ): PageData<Content>

}