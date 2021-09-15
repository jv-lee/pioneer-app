package com.lee.pioneer.home.view.fragment

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.lee.library.adapter.core.UiPager2Adapter
import com.lee.library.base.BaseVMFragment
import com.lee.library.extensions.setBackgroundColorCompat
import com.lee.library.extensions.setBackgroundDrawableCompat
import com.lee.library.extensions.setTextColorCompat
import com.lee.library.extensions.toast
import com.lee.library.tools.DarkViewUpdateTools
import com.lee.library.widget.StatusLayout
import com.lee.pioneer.home.R
import com.lee.pioneer.home.databinding.FragmentHomeBinding
import com.lee.pioneer.home.viewmodel.HomeViewModel
import com.lee.pioneer.library.service.SearchService
import com.lee.pioneer.library.service.hepler.ModuleService
import com.lee.pioneer.router.NavigationAnim
import com.lee.pioneer.router.navigationDeepLink

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 主页
 */
class HomeFragment :
    BaseVMFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home),
    DarkViewUpdateTools.ViewCallback {

    private var adapter: UiPager2Adapter? = null
    private var mediator: TabLayoutMediator? = null

    override fun bindView() {
        DarkViewUpdateTools.bindViewCallback(this, this)

        binding.run {
            status.setStatus(StatusLayout.STATUS_LOADING)
            status.setOnReloadListener {
                viewModel.buildCategoryFragment()
            }

            tvSearch.setOnClickListener {
                ModuleService.find<SearchService>().navigationSearch(findNavController())
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun bindData() {
        viewModel.run {
            //获取分类数据 构建分类tab 及 fragment
            categoryData.observe(this@HomeFragment, { it ->
                binding.status.setStatus(StatusLayout.STATUS_DATA)

                val fragments = arrayListOf<Fragment>()
                val titles = arrayListOf<String>()

                it.data.map {
                    titles.add(it.title)
                    fragments.add(ContentListFragment.newInstance(it.type))
                }

                binding.vpContainer.offscreenPageLimit = titles.size
                binding.vpContainer.adapter = UiPager2Adapter(this@HomeFragment, fragments).also {
                    adapter = it
                }

                TabLayoutMediator(binding.tabCategory, binding.vpContainer) { tab, position ->
                    tab.text = titles[position]
                }.also {
                    mediator = it
                }.attach()

            }, {
                toast(it)
                binding.status.setStatus(StatusLayout.STATUS_DATA_ERROR)
            })

            buildCategoryFragment()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mediator?.detach()
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
        binding.tabCategory.setSelectedTabIndicatorColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorAccent
            )
        )
    }

}
