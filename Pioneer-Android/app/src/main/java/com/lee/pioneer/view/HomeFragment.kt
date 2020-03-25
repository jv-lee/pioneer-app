package com.lee.pioneer.view

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.lee.library.base.BaseFragment
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentHomeBinding

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description
 */
class HomeFragment :
    BaseFragment<FragmentHomeBinding, ViewModel>(R.layout.fragment_home, null) {

    override fun bindData(savedInstanceState: Bundle?) {

    }

    override fun bindView() {
        binding.tvSearch.setOnClickListener { toast("start Search .") }
    }

}
