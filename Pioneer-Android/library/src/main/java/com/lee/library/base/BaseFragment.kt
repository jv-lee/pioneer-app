package com.lee.library.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import java.lang.reflect.ParameterizedType

/**
 * @author jv.lee
 * @date 2019/8/16.
 * @description
 */
abstract class BaseFragment<V : ViewDataBinding, VM : ViewModel>(
    var layoutId: Int,
    var vm: Class<VM>?
) : Fragment()
    , CoroutineScope by CoroutineScope(Dispatchers.Main) {

    protected lateinit var binding: V
    protected lateinit var viewModel: VM

    private var fistVisible = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(getChildClassName(),"onCreateView()")
        //设置viewBinding
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(getChildClassName(),"onViewCreated()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(getChildClassName(),"onActivityCreated()")
        //设置viewModel
        if (vm != null) viewModel = ViewModelProviders.of(this).get<VM>(vm!!)
        intentParams(arguments)
        bindView()
        bindData(savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        Log.i(getChildClassName(),"onResume()")
        if (fistVisible) {
            fistVisible = false
            lazyLoad()
        }
    }

    @ExperimentalCoroutinesApi
    override fun onDetach() {
        super.onDetach()
        Log.i(getChildClassName(),"onDetach()")
        cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(getChildClassName(),"onDestroyView()")
    }

    /**
     * 初始化参数传递
     */
    open fun intentParams(arguments: Bundle?) {}


    /**
     * 设置加载数据等业务操作
     *
     * @param savedInstanceState 重置回调参数
     */
    protected abstract fun bindData(savedInstanceState: Bundle?)

    /**
     * 设置view基础配置
     */
    protected abstract fun bindView()

    /**
     * 使用page 多fragment时 懒加载
     */
    open fun lazyLoad() {
        Log.i(getChildClassName(),"lazyLoad()")
    }

    fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(activity, message, duration).show()
    }

    private fun getChildClassName(): String {
        return javaClass.simpleName
    }

}