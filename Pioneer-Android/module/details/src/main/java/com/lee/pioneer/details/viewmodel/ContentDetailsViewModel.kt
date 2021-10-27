package com.lee.pioneer.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.viewmodel.CoroutineViewModel
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService

/**
 * @author jv.lee
 * @date 2020/3/31
 * @description
 */
class ContentDetailsViewModel : CoroutineViewModel() {

    private val meService by lazy { ModuleService.find<MeService>() }

    private val _favoriteLive = MutableLiveData<Int>()
    val favoriteLive: LiveData<Int> = _favoriteLive

    fun contentCollect(id: String) {
        launchMain {
            launchIO {
                val isCollect = meService.isCollect(id) == 1

                //已收藏
                if (isCollect) {
                    _favoriteLive.postValue(0)
                    return@launchIO
                }

                //添加收藏夹
                val content = meService.queryContentById(id)[0]
                content.isCollect = 1
                meService.insert(content)
                _favoriteLive.postValue(1)
            }
        }
    }

}