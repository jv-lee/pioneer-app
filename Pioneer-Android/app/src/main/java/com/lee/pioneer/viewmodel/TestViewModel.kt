package com.lee.pioneer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.model.repository.TestRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * @author jv.lee
 * @date 2020/10/13
 * @description
 */
class TestViewModel : BaseViewModel() {

    val repository: TestRepository = TestRepository()

    val bannerLiveData by lazy {
        flow {
            emit(repository.getBanner2())
        }.catch {
            failedEvent.value = it
        }.map {
            it.data
        }.asLiveData()
    }

    val bannerLiveData2 by lazy { MutableLiveData<ArrayList<Banner>>() }

    fun getBanner() {
        launchMain {
            flow {
                emit(repository.getBanner2())
            }.map {
                it.data
            }.collect {
                bannerLiveData2.value = it
            }
        }
    }

}