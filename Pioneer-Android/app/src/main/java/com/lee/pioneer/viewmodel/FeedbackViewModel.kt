package com.lee.pioneer.viewmodel

import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.lee.library.base.BaseApplication
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.widget.interadap.TextWatcherAdapter
import com.lee.pioneer.App
import com.lee.pioneer.R

/**
 * @author jv.lee
 * @date 2020/4/24
 * @description
 */
class FeedbackViewModel : BaseViewModel() {

    private val contentField by lazy { ObservableField<String>("") }
    private val linkField by lazy { ObservableField<String>("") }
    val toastStrObserver by lazy { MutableLiveData<String>() }

    val etContentWatcher = object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            contentField.set(s.toString())
        }

    }

    val etLinkWatcher = object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            super.onTextChanged(s, start, before, count)
            linkField.set(s.toString())
        }
    }

    fun commit() {
        if (TextUtils.isEmpty(contentField.get()) || TextUtils.isEmpty(linkField.get())) {
            toastStrObserver.value = getApplication<App>().getString(R.string.feedback_empty)
            return
        }
        toastStrObserver.value = getApplication<App>().getString(R.string.feedback_success)
    }

}