package com.lee.pioneer.tools

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
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
        optionsCommand = RequestOptions()
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontTransform()
            .placeholder(R.mipmap.ic_picture_temp)
    }

    var cacheArray = arrayListOf<Any>()

    fun loadCenterCopy(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .asDrawable()
            .load(path)
            .apply(optionsCommand)
            .transition(DrawableTransitionOptions.withCrossFade(DrawableCrossFadeFactory.Builder(150)))
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

}