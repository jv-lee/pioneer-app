package com.lee.pioneer.view.fragment

import androidx.lifecycle.ViewModel
import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.LaunchActivity
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentFavoriteBinding

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 收藏页面
 */
class FavoriteFragment :
    BaseNavigationFragment<FragmentFavoriteBinding, ViewModel>(R.layout.fragment_favorite, null) {

    override fun bindView() {

    }

    override fun bindData() {
    }

    override fun onResume() {
        super.onResume()
        (activity as LaunchActivity).showView()
    }

}
