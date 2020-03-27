package com.lee.library.base

import android.os.Bundle
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

/**
 * @author jv.lee
 * @date 2019/8/16.
 * @description 解决使用navigation框架 重新构建view的baseFragment
 */
abstract class BaseNavigationFragment<V : ViewDataBinding, VM : ViewModel>(
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
        //设置viewBinding
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //设置viewModel
        if (vm != null) viewModel = ViewModelProviders.of(this).get<VM>(vm!!)
        intentParams(arguments, savedInstanceState)
        bindView()
    }


    override fun onResume() {
        super.onResume()
        if (fistVisible) {
            fistVisible = false
            bindData()
        }
    }

    @ExperimentalCoroutinesApi
    override fun onDetach() {
        super.onDetach()
        cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    /**
     * 初始化参数传递
     */
    open fun intentParams(arguments: Bundle?, savedInstanceState: Bundle?) {}

    /**
     * 设置view基础配置
     */
    protected abstract fun bindView()

    /**
     * 设置加载数据等业务操作
     *
     * @param savedInstanceState 重置回调参数
     */
    protected abstract fun bindData()

    fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(activity, message, duration).show()
    }

    private fun getChildClassName(): String {
        return javaClass.simpleName
    }

}