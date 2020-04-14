package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.PageData
import com.lee.pioneer.model.repository.ApiRepository
import executeResponseAny
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlin.random.Random

/**
 * @author jv.lee
 * @date 2020/4/10
 * @description
 */
class GirlViewModel(application: Application) : BaseViewModel(application) {

    var page = 0
    val contentObservable by lazy { MutableLiveData<PageData<Content>>() }

    fun getGirlContentData(isMore: Boolean) {
        if (!isMore) page = 0
        launch(-1) {
            //数据转换 添加viewType
            flow {
                val responseAsync =
                    ApiRepository.getApi().getCategoryDataAsync("Girl", "Girl", ++page, 20)
                emit(responseAsync.await())
            }.map { it ->
                it.data.forEach { it.viewType = Random.nextInt() % 2 }
                it
            }.flowOn(Dispatchers.IO).collect { it ->
                executeResponseAny(it, { contentObservable.value = it })
            }
        }
    }

}