package com.lee.pioneer.search.viewmodel

import android.app.Activity
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.widget.TextView
import androidx.lifecycle.*
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.mvvm.livedata.LoadPageLiveData
import com.lee.library.mvvm.ui.UiState
import com.lee.library.mvvm.ui.stateLive
import com.lee.library.tools.KeyboardTools
import com.lee.pioneer.search.model.repository.ApiRepository
import kotlinx.coroutines.flow.*

/**
 * @author jv.lee
 * @date 2020/4/7
 * @description
 */
class SearchViewModel : BaseViewModel() {

    private val repository = ApiRepository()

    //搜索内容及页码 根据页码变化进行内容搜索
    private lateinit var searchText: String

    //分页状态
    val pageLive = LoadPageLiveData(1)

    //搜索数据结果根据分页进行加载
    val contentLive: LiveData<UiState> = pageLive.switchMap { input ->
        stateLive {
            repository.api.getSearchDataAsync(searchText, input)
        }
    }

    //键盘搜索按钮事件监听
    val editActionListener = TextView.OnEditorActionListener { view, actionId, _ ->
        if (actionId == IME_ACTION_SEARCH) {
            val text = view.text.toString()
            if (text.isNotEmpty()) {
                searchText = text
                pageLive.refresh()
                KeyboardTools.hideSoftInput(view.context as Activity)
            }
        }
        return@OnEditorActionListener false
    }

}