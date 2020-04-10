package com.lee.pioneer.view.fragment

import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentMeBinding
import com.lee.pioneer.viewmodel.MeViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 我的页面
 */
class MeFragment :
    BaseNavigationFragment<FragmentMeBinding, MeViewModel>(
        R.layout.fragment_me,
        MeViewModel::class.java
    ) {

    override fun bindView() {

    }

    override fun bindData() {

    }

    override fun onResume() {
        super.onResume()
        showNavigation()
    }

}
