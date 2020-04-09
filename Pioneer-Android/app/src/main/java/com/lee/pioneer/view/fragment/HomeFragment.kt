package com.lee.pioneer.view.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lee.library.adapter.UiPagerAdapter
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.widget.StatusLayout
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentHomeBinding
import com.lee.pioneer.viewmodel.HomeViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description 主页
 */
class HomeFragment : BaseNavigationFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home,
    HomeViewModel::class.java
) {

    private val vpAdapter by lazy {
        UiPagerAdapter(childFragmentManager, ArrayList<Fragment>(), ArrayList<String>())
    }

    override fun bindView() {
        binding.status.setStatus(StatusLayout.STATUS_LOADING)
        binding.tvSearch.setOnClickListener {
            hideNavigation()
            findNavController().navigate(R.id.action_home_to_search)
        }
        binding.vpContainer.adapter = vpAdapter
        binding.tabCategory.setupWithViewPager(binding.vpContainer)
    }

    override fun bindData() {
        viewModel.apply {
            //获取分类数据 构建分类tab 及 fragment
            categoryObservable.observe(this@HomeFragment, Observer { it ->
                binding.status.setStatus(StatusLayout.STATUS_DATA)
                it.map {
                    vpAdapter.tabList.add(it.title)
                    vpAdapter.fragmentList.add(ContentListFragment.newInstance(it.type))
                }
                vpAdapter.notifyDataSetChanged()
                binding.vpContainer.offscreenPageLimit = vpAdapter.count - 1
            })

            //获取
            failedEvent.observe(this@HomeFragment, Observer {
                when (it.code) {
                    -1 -> {
                        toast("请求错误:${it.message}  ")
                        binding.status.setStatus(StatusLayout.STATUS_DATA_ERROR)
                    }
                }
            })
        }
        viewModel.buildCategoryFragment()
    }

    override fun onResume() {
        super.onResume()
        showNavigation()
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

}
