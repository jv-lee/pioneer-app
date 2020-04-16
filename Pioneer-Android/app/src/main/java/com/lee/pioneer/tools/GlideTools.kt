package com.lee.pioneer.tools

import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition

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
        optionsCommand = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
    }

    var cacheArray = arrayListOf<Any>()

    fun loadCenterCopy(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(path)
            .apply(optionsCommand.centerCrop())
            .into(imageView)
//        if (cacheArray.contains(path)) {
//            Glide.with(imageView.context)
//                .load(path)
//                .apply(optionsCommand.centerCrop())
//                .into(imageView)
//        } else {
//            path?.let { cacheArray.add(it) }
//            Glide.with(imageView.context)
//                .load(path)
//                .apply(optionsCommand.centerCrop())
//                .transition(DrawableTransitionOptions.withCrossFade(DrawableCrossFadeFactory.Builder(100)))
//                .into(imageView)
//        }
    }

    fun loadCenterInside(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(path)
            .apply(optionsCommand.centerInside())
            .into(imageView)
    }

    fun loadFitCenter(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(path)
            .apply(optionsCommand.fitCenter())
            .into(imageView)
    }

    fun loadCircleCrop(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(path)
            .apply(optionsCommand.circleCrop())
            .into(imageView)
    }

    fun loadCircle(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(path)
            .apply(optionsCommand.apply(RequestOptions.bitmapTransform(CircleCrop())))
            .into(imageView)
    }

    fun loadRound(path: Any?, imageView: ImageView, radius: Int) {
        Glide.with(imageView.context)
            .load(path)
            .apply(optionsCommand.apply(RequestOptions.bitmapTransform(RoundedCorners(radius))))
            .into(imageView)
    }

}