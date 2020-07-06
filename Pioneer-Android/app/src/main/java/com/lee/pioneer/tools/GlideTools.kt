package com.lee.pioneer.tools

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lee.library.base.BaseApplication
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
    private var placeholderResId: Int? = null
    private val loadDuration = 300
    private val animEnableArray = arrayListOf<Int>()

    fun updatePlaceholder() {
        placeholderResId = if (DarkModeTools.get().isDarkTheme()) {
            R.mipmap.ic_picture_temp_night
        } else {
            R.mipmap.ic_picture_temp
        }
    }

    private fun initOptions() {
        updatePlaceholder()
        //初始化普通加载
        RequestOptions()
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.IMMEDIATE)
            .dontTransform()
            .dontAnimate()
            .also {
                optionsCommand = it
            }
    }

    @SuppressLint("CheckResult")
    fun loadImage(path: String?, imageView: ImageView) {
        var animEnable = animEnableArray.contains(path.hashCode())
        val request = Glide.with(imageView.context)
            .load(http2https(path))
            .apply(optionsCommand.placeholder(placeholderResId!!))

        //通过tag判断是否为第一次加载 首次加载使用动画显示
        if (!animEnable) {
            animEnableArray.add(path.hashCode())
            request.transition(
                DrawableTransitionOptions.withCrossFade().crossFade(loadDuration)
            )
        }
        request.into(imageView)
    }

    fun loadPlaceholderImage(
        path: String?, @DrawableRes placeholderResId: Int,
        imageView: ImageView
    ) {
        path?.let {
            //            imageView.setBackgroundResource(placeholderResId)
            loadImage(path, imageView)
        }
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