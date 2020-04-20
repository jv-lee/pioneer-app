package com.lee.pioneer.tools

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lee.pioneer.R

/**
 * @author jv.lee
 * @date 2020/3/30
 * @description
 */
class GlideTools {

    companion object {
        @Volatile
        private var instance: GlideTools? = null

        @JvmStatic
        fun get() = instance ?: synchronized(this) {
            instance ?: GlideTools().also { instance = it }
        }
    }

    init {
        initOptions()
    }

    private lateinit var optionsCommand: RequestOptions

    private lateinit var smallCommend: RequestOptions

    private lateinit var bigCommend: RequestOptions

    private fun initOptions() {
        //初始化普通加载
        RequestOptions()
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.IMMEDIATE)
            .dontTransform()
            .placeholder(R.mipmap.ic_picture_temp)
            .also {
                optionsCommand = it
                smallCommend = it
                smallCommend.override(150, 150)
                bigCommend = it
                bigCommend.override(360, 360)
            }
    }

    var cacheArray = arrayListOf<Any>()

    fun loadImage(path: Any?, imageView: ImageView) {
        val request = Glide.with(imageView.context)
            .asDrawable()
            .load(path)
            .apply(optionsCommand)
        request.into(imageView)
    }

    fun loadSmallImage(path: Any?, imageView: ImageView) {
        val request = Glide.with(imageView.context)
            .asDrawable()
            .load(path)
            .apply(smallCommend)
        request.into(imageView)
    }

    fun loadBigImage(path: Any?, imageView: ImageView) {
        val request = Glide.with(imageView.context)
            .asDrawable()
            .load(path)
            .apply(bigCommend)
        request.into(imageView)
    }

}