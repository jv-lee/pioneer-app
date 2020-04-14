package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.model.entity.Category
import com.lee.pioneer.model.entity.PageData
import com.lee.pioneer.model.repository.ApiRepository
import executeResponse
import executeResponseAny

/**
 * @author jv.lee
 * @date 2020/3/26
 * @description 主页ViewModel
 */
class HomeViewModel(application: Application) : BaseViewModel(application) {

    val categoryObservable by lazy { MutableLiveData<PageData<Category>>() }

    /**
     * TODO 构建主页分类tab 子fragments
     */
    fun buildCategoryFragment() {
        launch(-1) {
            executeResponseAny(ApiRepository.getApi().getCategoriesAsync(KeyConstants.CATEGORY_TYPE).await()) {
                categoryObservable.value = it
            }
        }


    }

}