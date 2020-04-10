package com.lee.pioneer.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.lee.library.base.BaseNavigationFragment
import com.lee.pioneer.LaunchActivity
import com.lee.pioneer.R
import com.lee.pioneer.constants.KeyConstants
import com.lee.pioneer.databinding.FragmentRecommendBinding
import com.lee.pioneer.model.entity.Banner
import com.lee.pioneer.view.widget.BannerViewHolder
import com.lee.pioneer.viewmodel.RecommendViewModel
import java.lang.Exception

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 推荐页面
 */
class RecommendFragment :
    BaseNavigationFragment<FragmentRecommendBinding, RecommendViewModel>(
        R.layout.fragment_recommend,
        RecommendViewModel::class.java
    ) {

    override fun bindView() {
        binding.tvSearch.setOnClickListener {
            hideNavigation()
            findNavController().navigate(R.id.action_recommend_to_search)
        }
        binding.banner.setDelayedTime(5000)
        binding.banner.setBannerPageClickListener { _, position ->
            (binding.banner.data[position] as Banner).let {
                hideNavigation()
                findNavController().navigate(
                    RecommendFragmentDirections.actionRecommendToContentDetails(
                        KeyConstants.CONST_EMPTY,
                        it.url
                    )
                )
            }
        }
    }

    override fun bindData() {
        viewModel.apply {
            bannerObservable.observe(this@RecommendFragment, Observer {
                binding.banner.setPages(it) { BannerViewHolder() }
                binding.banner.start()
            })
        }
    }

    override fun lazyLoad() {
        viewModel.getBannerData()
    }

    override fun onResume() {
        super.onResume()
        showNavigation()
        binding.banner.let {
            binding.banner.pause()
            binding.banner.start()
        }
    }

    override fun onPause() {
        super.onPause()
        binding.banner.let {
            binding.banner.pause()
        }
    }

}
