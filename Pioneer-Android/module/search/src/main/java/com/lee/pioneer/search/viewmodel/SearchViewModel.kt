package com.lee.pioneer.search.viewmodel

import android.app.Activity
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.widget.TextView
import androidx.lifecycle.*
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.mvvm.livedata.SingleLiveData
import com.lee.library.mvvm.load.PageNumberLiveData
import com.lee.library.mvvm.ui.UiState
import com.lee.library.mvvm.ui.stateLiveData
import com.lee.library.utils.KeyboardUtil
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_ALL
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.search.model.repository.ApiRepository
import kotlinx.coroutines.flow.*

/**
 * @author jv.lee
 * @date 2020/4/7
 * @description
 */
class SearchViewModel : BaseViewModel() {

    private val repository by lazy { ApiRepository() }

    private lateinit var searchText: String

    val pageLive = PageNumberLiveData(1)
    val loadingLive = SingleLiveData<Boolean>()

    val contentLive = Transformations.switchMap(pageLive) { input ->
        stateLiveData {
            repository.api.getSearchDataAsync(searchText, input)
        }
    }

    val editActionListener = TextView.OnEditorActionListener { view, actionId, _ ->
        if (actionId == IME_ACTION_SEARCH) {
            val text = view.text.toString()
            if (text.isNotEmpty()) {
                searchText = text
                loadingLive.postValue(true)
                pageLive.refresh()
                KeyboardUtil.hideSoftInput(view.context as Activity)
            }
        }
        return@OnEditorActionListener false
    }

}