package com.lee.pioneer.search.viewmodel

import android.app.Activity
import android.text.TextUtils
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.lee.library.mvvm.base.BaseLiveData
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.utils.KeyboardUtil
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_ALL
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CONST_EMPTY
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.library.common.entity.Content
import com.lee.pioneer.library.common.entity.PageData
import com.lee.pioneer.search.model.repository.ApiRepository

/**
 * @author jv.lee
 * @date 2020/4/7
 * @description
 */
class SearchViewModel : BaseViewModel() {

    private val repository by lazy { ApiRepository() }

    private var page = 0
    private val searchTextObservable = ObservableField<String>(CONST_EMPTY)
    val contentListObservable by lazy { BaseLiveData<PageData<Content>>() }
    val loadingObservable by lazy { MutableLiveData<Boolean>() }

    val editActionListener = TextView.OnEditorActionListener { view, actionId, _ ->
        if (actionId == IME_ACTION_SEARCH) {
            searchTextObservable.set(view.text.toString())
            loadingObservable.value = true
            searchDataList(false)
            KeyboardUtil.hideSoftInput(view.context as Activity)
        }
        return@OnEditorActionListener false
    }

    fun searchDataList(isLoadMore: Boolean) {
        if (TextUtils.isEmpty(searchTextObservable.get())) return
        if (!isLoadMore) page = 0
        launchMain {
            val text = searchTextObservable.get()!!
            val response =
                repository.api.getSearchDataAsync(
                    text,
                    CATEGORY_ALL,
                    CATEGORY_ALL,
                    ++page,
                    PAGE_COUNT
                )
            contentListObservable.value = response
        }
    }


}