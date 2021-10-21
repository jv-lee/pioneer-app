package com.lee.library.mvvm.load

import androidx.lifecycle.LiveData

/**
 * @author jv.lee
 * @date 2021/9/18
 * @description
 */
class PageNumberLiveData(var initPage: Int = 0) : LiveData<Int>() {

    private var page = initPage

    private fun load(@LoadStatus status: Int) {
        value = when (status) {
            LoadStatus.INIT, LoadStatus.REFRESH -> {
                page = initPage
                page
            }
            LoadStatus.RELOAD -> {
                page
            }
            LoadStatus.LOAD_MORE -> {
                ++page
            }
            else -> page
        }
    }

    fun refresh() {
        load(LoadStatus.REFRESH)
    }

    fun reload() {
        load(LoadStatus.RELOAD)
    }

    fun loadMore() {
        load(LoadStatus.LOAD_MORE)
    }

}