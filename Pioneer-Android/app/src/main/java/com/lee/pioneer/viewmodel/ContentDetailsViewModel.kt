package com.lee.pioneer.viewmodel

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
class ContentDetailsViewModel : BaseViewModel() {

    val favoriteObservable by lazy { MutableLiveData<Int>() }

    fun contentCollect(id: String) {
        launchMain {
            //查询是否收藏
            val isCollect =
                launchIO {
                    DataBaseRepository.get().historyDao.isCollect(id)
                }
            if (isCollect == 1) {
                favoriteObservable.value = 0
                return@launchMain
            }

            //添加收藏夹
            val content =
                launchIO {
                    DataBaseRepository.get().historyDao.queryContentById(id)[0]
                }
            launchIO {
                content.isCollect = 1
                DataBaseRepository.get().historyDao.insert(content)
            }
            favoriteObservable.value = 1
        }
    }

}