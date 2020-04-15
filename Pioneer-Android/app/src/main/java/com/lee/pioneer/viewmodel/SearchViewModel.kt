package com.lee.pioneer.viewmodel

import android.app.Activity
import android.app.Application
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.BaseViewModel
import com.lee.library.utils.KeyboardUtil
import com.lee.pioneer.constants.KeyConstants.Companion.CATEGORY_ALL
import com.lee.pioneer.constants.KeyConstants.Companion.CONST_EMPTY
import com.lee.pioneer.constants.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.model.entity.Content
import com.lee.pioneer.model.entity.PageData
import com.lee.pioneer.model.repository.ApiRepository
import executeResponseAny

/**
 * @author jv.lee
 * @date 2020/4/7
 * @description
 */
class SearchViewModel(application: Application) : BaseViewModel(application) {

    private var page = 0
    private val searchTextObservable = ObservableField<String>(CONST_EMPTY)
    val contentListObservable by lazy { MutableLiveData<PageData<Content>>() }
    val loadingObservable by lazy { MutableLiveData<Boolean>() }

    val editActionListener = TextView.OnEditorActionListener { view, actionId, _ ->
        if (actionId == IME_ACTION_SEARCH) {
            searchTextObservable.set(view.text.toString())
            loadingObservable.value = true
            searchDataList(false)
            KeyboardUtil.hideSoftInput(view.context as Activity?)
        }
        return@OnEditorActionListener false
    }

    fun searchDataList(isLoadMore: Boolean) {
        if (searchTextObservable.get() == "") return
        if (!isLoadMore) page = 0
        launch(-1) {
            val text = searchTextObservable.get()!!
            val response =
                ApiRepository.getApi()
                    .getSearchDataAsync(text, CATEGORY_ALL, CATEGORY_ALL, ++page, PAGE_COUNT)
                    .await()
            executeResponseAny(response) { contentListObservable.value = it }
        }
    }


}