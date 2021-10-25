package com.lee.pioneer.me.viewmodel

import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lee.library.base.BaseApplication
import com.lee.library.mvvm.viewmodel.CoroutineViewModel
import com.lee.library.widget.interadap.TextWatcherAdapter
import com.lee.pioneer.me.R

/**
 * @author jv.lee
 * @date 2020/4/24
 * @description
 */
class FeedbackViewModel : CoroutineViewModel() {

    private val contentField by lazy { ObservableField("") }
    private val linkField by lazy { ObservableField("") }

    private val _toastLive = MutableLiveData<String>()
    val toastLive: LiveData<String> = _toastLive

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
            _toastLive.value = BaseApplication.getContext().getString(R.string.feedback_empty)
            return
        }
        _toastLive.value = BaseApplication.getContext().getString(R.string.feedback_success)
    }

}