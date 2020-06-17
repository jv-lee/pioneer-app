package com.lee.pioneer.tools

import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * @author jv.lee
 * @date 2020/4/7
 * @description
 */
@BindingAdapter("android:editActionListener")
fun setEditActionListener(view: EditText, listener: TextView.OnEditorActionListener) {
    view.setOnEditorActionListener(listener)
}

@BindingAdapter("android:checkedChangeListener")
fun setOnCheckedChangeListener(view:Switch,listener:CompoundButton.OnCheckedChangeListener){
    view.setOnCheckedChangeListener(listener)
}