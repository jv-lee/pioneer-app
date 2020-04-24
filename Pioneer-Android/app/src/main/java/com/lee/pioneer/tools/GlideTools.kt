package com.lee.pioneer.tools

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.lee.library.utils.LogUtil
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

    private fun initOptions() {
        //初始化普通加载
        RequestOptions()
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.IMMEDIATE)
            .dontTransform()
            .also {
                optionsCommand = it
            }
    }

    var cacheArray = arrayListOf<Any>()

    @SuppressLint("CheckResult")
    fun loadImage(path: String?, imageView: ImageView) {
        val request = Glide.with(imageView.context)
            .asDrawable()
            .load(http2https(path))
            .apply(optionsCommand)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.background = null
                    return false
                }

            })
        if (!cacheArray.contains(path.hashCode())) {
            request.transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder(
                        150
                    )
                )
            )
            cacheArray.add(path.hashCode())
        }
        request.into(imageView)
    }

    private fun http2https(path: Any?): Any? {
        path?.let {
            if (path is String && path.startsWith("http://")) {
                return path.replace("http://", "https://")
            }
        }
        return path
    }

}