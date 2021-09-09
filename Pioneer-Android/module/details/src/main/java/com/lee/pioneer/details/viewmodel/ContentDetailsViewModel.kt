package com.lee.pioneer.details.viewmodel

import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService

/**
 * @author jv.lee
 * @date 2020/3/31
 * @description
 */
class ContentDetailsViewModel : BaseViewModel() {

    private val meService by lazy { ModuleService.find<MeService>() }
    val favoriteObservable by lazy { MutableLiveData<Int>() }

    fun contentCollect(id: String) {
        launchMain {
            //查询是否收藏
            val isCollect = launchIO { meService.isCollect(id) }

            if (isCollect == 1) {
                favoriteObservable.value = 0
                return@launchMain
            }

            launchIO {
                //添加收藏夹
                val content = meService.queryContentById(id)[0]
                content.isCollect = 1
                meService.insert(content)
            }
            favoriteObservable.value = 1
        }
    }

}