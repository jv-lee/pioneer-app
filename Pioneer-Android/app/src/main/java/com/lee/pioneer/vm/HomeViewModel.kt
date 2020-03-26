package com.lee.pioneer.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import call
import com.lee.library.mvvm.BaseViewModel
import com.lee.library.utils.LogUtil
import com.lee.pioneer.const.KeyConst
import com.lee.pioneer.http.ApiImpl
import com.lee.pioneer.model.Category
import java.lang.Exception

/**
 * @author jv.lee
 * @date 2020/3/26
 * @description
 */
class HomeViewModel(application: Application) : BaseViewModel(application) {

    val failedCodeObservable by lazy { MutableLiveData<Int>() }
    val categoryObservable by lazy { MutableLiveData<List<Category>>() }

    fun buildCategoryFragment() {
        launch {
            val response = ApiImpl.getInstance().getCategoriesAsync(KeyConst.CATEGORY_TYPE).await()
            LogUtil.i("entry response $response")
            call(response, {
                categoryObservable.value = it
            }, {
                failedCodeObservable.value = 0
            })
        }
    }

}