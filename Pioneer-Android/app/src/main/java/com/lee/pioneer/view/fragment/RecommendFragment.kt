package com.lee.pioneer.view.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.lee.library.base.BaseFragment
import com.lee.library.utils.LogUtil
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentRecommendBinding

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description
 */
class RecommendFragment :
    BaseFragment<FragmentRecommendBinding, ViewModel>(R.layout.fragment_recommend, null) {

    override fun bindView() {

    }

    override fun bindData(savedInstanceState: Bundle?) {
    }

    override fun lazyLoad() {
        super.lazyLoad()

    }

}
