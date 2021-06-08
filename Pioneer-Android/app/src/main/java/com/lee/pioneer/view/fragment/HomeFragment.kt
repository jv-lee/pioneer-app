package com.lee.pioneer.view.fragment

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lee.library.adapter.core.UiPagerAdapter
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.library.extensions.setBackgroundDrawableCompat
import com.lee.library.extensions.setTextColorCompat
import com.lee.library.widget.StatusLayout
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentHomeBinding
import com.lee.pioneer.tools.DarkViewUpdateTools
import com.lee.pioneer.viewmodel.HomeViewModel
import com.lee.pioneer.viewmodel.TestViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 主页
 */
class HomeFragment :
    BaseNavigationFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home),DarkViewUpdateTools.ViewCallback {

    val testViewModel by lazy { createViewModel(TestViewModel::class.java) }

    private val vpAdapter by lazy {
        UiPagerAdapter(
            childFragmentManager,
            ArrayList<Fragment>(),
            ArrayList<String>()
        )
    }

    override fun bindView() {
        DarkViewUpdateTools.bindViewCallback(this, this)

        binding.run {
            status.setStatus(StatusLayout.STATUS_LOADING)
            status.setOnReloadListener {
                viewModel.buildCategoryFragment()
            }
            tvSearch.setOnClickListener {
                findNavController().navigate(R.id.action_main_to_search)
            }
            vpContainer.adapter = vpAdapter
            tabCategory.setupWithViewPager(binding.vpContainer)
        }
    }

    override fun bindData() {
        viewModel.run {
            //获取分类数据 构建分类tab 及 fragment
            categoryData.observe(this@HomeFragment, Observer { it ->
                binding.status.setStatus(StatusLayout.STATUS_DATA)
                vpAdapter.tabList.clear()
                vpAdapter.fragmentList.clear()

                it.data.map {
                    vpAdapter.tabList.add(it.title)
                    vpAdapter.fragmentList.add(ContentListFragment.newInstance(it.type))
                }

                vpAdapter.notifyDataSetChanged()
                binding.vpContainer.offscreenPageLimit = vpAdapter.count - 1
            }, Observer {
                toast(it)
                if (vpAdapter.tabList.isEmpty()) {
                    binding.status.setStatus(StatusLayout.STATUS_DATA_ERROR)
                }
            })

            //viewPager恢复页面
            restoreHomePageLiveData.bindPager(this@HomeFragment, binding.vpContainer)

            buildCategoryFragment()
        }

//        testViewModel.bannerLiveData.observe(this, Observer {
//            toast("size:${it.size}")
//        })
//        testViewModel.bannerLiveData2.observe(this, Observer {
//            toast("size:${it.size}")
//            LogUtil.i(it.toString())
//        })
//
//        testViewModel.failedEvent.observe(this, Observer {
//            toast(it.message)
//        })
//        testViewModel.getBanner()

    }

    override fun onResume() {
        super.onResume()
        //重新更新view
        if (binding.vpContainer.childCount == 0) {
            vpAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //清除view引用
        binding.vpContainer.removeAllViews()
    }

    override fun updateDarkView() {
        binding.constContainer.setBackgroundColorCompat(R.color.colorThemeBackground)
        binding.toolbar.setBackgroundColorCompat(R.color.colorThemeItem)

        binding.tvSearch.setBackgroundDrawableCompat(R.drawable.shape_theme_search)
        binding.tvSearch.setTextColorCompat(R.color.colorThemePrimary)

        binding.tabCategory.setBackgroundColorCompat(R.color.colorThemeItem)
        binding.tabCategory.setTabTextColors(
            ContextCompat.getColor(requireContext(), R.color.colorThemePrimary),
            ContextCompat.getColor(requireContext(), R.color.colorAccent)
        )
    }

}
