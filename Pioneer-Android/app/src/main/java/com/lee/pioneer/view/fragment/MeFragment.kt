package com.lee.pioneer.view.fragment

import androidx.lifecycle.ViewModel
import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.LaunchActivity
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentMeBinding

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 我的页面
 */
class MeFragment :
    BaseNavigationFragment<FragmentMeBinding, ViewModel>(R.layout.fragment_me, null) {

    override fun bindView() {

    }

    override fun bindData() {
    }


    override fun onResume() {
        super.onResume()
        showNavigation()
    }

}
