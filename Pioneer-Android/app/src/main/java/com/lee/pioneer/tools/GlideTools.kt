package com.lee.pioneer.tools

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

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
            .dontAnimate()
            .dontTransform()
    }

    var cacheArray = arrayListOf<Any>()

    fun loadCenterCopy(path: Any?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(path)
            .apply(optionsCommand)
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