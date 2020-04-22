package com.lee.pioneer.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.pioneer.model.repository.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author jv.lee
 * @date 2020/3/31
 * @description
 */
class ContentDetailsViewModel(application: Application) : BaseViewModel(application) {

    val favoriteObservable by lazy { MutableLiveData<Int>() }

    fun insertFavorite(id: String) {
        launch {
            //查询是否收藏
            val isFavorite =
                withContext(Dispatchers.IO) {
                    DataBaseRepository.get().historyDao.isFavorite(id)
                }
            if (isFavorite == 1) {
                favoriteObservable.value = 0
                return@launch
            }

            //添加收藏夹
            val content =
                withContext(Dispatchers.IO) {
                    DataBaseRepository.get().historyDao.queryContentById(id)[0]
                }
            withContext(Dispatchers.IO) {
                content.isFavorite = 1
                DataBaseRepository.get().historyDao.insert(content)
            }
            favoriteObservable.value = 1
        }
    }

}