package com.lee.pioneer.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.lee.library.base.BaseFragment
import com.lee.pioneer.R
import com.lee.pioneer.const.KeyConst
import com.lee.pioneer.databinding.FragmentHomeBinding
import com.lee.pioneer.http.ApiImpl
import com.lee.pioneer.vm.HomeViewModel
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description
 */
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(
        R.layout.fragment_home,
        HomeViewModel::class.java
    ) {

    override fun bindData(savedInstanceState: Bundle?) {
        viewModel.apply {
            categoryObservable.observe(this@HomeFragment, Observer {
                toast("list.size->" + it.size)
            })
            failedCodeObservable.observe(this@HomeFragment, Observer {
                toast("failed response")
            })

            mException.observe(this@HomeFragment, Observer {
                toast("failed response")
            })
        }

        viewModel.buildCategoryFragment()
    }

    override fun bindView() {
        binding.tvSearch.setOnClickListener { toast("start Search .") }
    }

}
