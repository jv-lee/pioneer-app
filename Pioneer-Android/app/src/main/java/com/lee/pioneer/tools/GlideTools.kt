package com.lee.pioneer.tools

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
            .priority(Priority.HIGH)
            .dontTransform()
            .placeholder(R.mipmap.ic_picture_temp)
            .also {
                optionsCommand = it
                smallCommend = it
                smallCommend.override(200, 200)
                bigCommend = it
                bigCommend.override(360, 360)
            }
    }

    var cacheArray = arrayListOf<Any>()

    fun loadImage(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .asDrawable()
            .load(path)
            .apply(optionsCommand)
            .into(imageView)
    }

    fun loadSmallImage(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .asDrawable()
            .load(path)
            .apply(smallCommend)
            .into(imageView)
    }

    fun loadBigImage(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .asDrawable()
            .load(path)
            .apply(bigCommend)
            .into(imageView)
    }

}