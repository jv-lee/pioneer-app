package com.lee.pioneer.view.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.lee.library.base.BaseFragment
import com.lee.library.utils.LogUtil
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentMeBinding

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description
 */
class MeFragment :
    BaseFragment<FragmentMeBinding, ViewModel>(R.layout.fragment_me, null) {

    override fun bindView() {

    }

    override fun bindData(savedInstanceState: Bundle?) {
    }

    override fun lazyLoad() {
        super.lazyLoad()

    }

}
