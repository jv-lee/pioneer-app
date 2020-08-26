package com.lee.pioneer.view.recommend

import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020/8/26
 * @description
 */
class ComicViewModel : BaseViewModel() {

    val dataObservable by lazy { MutableLiveData<ArrayList<Recommend>>() }

    fun getData() {
        launch {
            delay(1500)
            val testMall = TestRepository.testMall()
            if (dataObservable.value != null) {
                val value = dataObservable.value
                value?.addAll(testMall)
                dataObservable.value = value
            } else {
                dataObservable.value = testMall
            }
        }


    }

}