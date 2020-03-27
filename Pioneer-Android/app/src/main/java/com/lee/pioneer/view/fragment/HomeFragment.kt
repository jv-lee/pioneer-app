package com.lee.pioneer.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lee.library.adapter.UiPagerAdapter
import com.lee.library.base.BaseFragment
import com.lee.library.utils.LogUtil
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentHomeBinding
import com.lee.pioneer.tools.TabLayoutUtil
import com.lee.pioneer.viewmodel.HomeViewModel

/**
 * @author jv.lee
 * @date 2020/3/24
 * @description
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home,
    HomeViewModel::class.java
) {

    private val vpAdapter by lazy { UiPagerAdapter(childFragmentManager, fragments, titles) }
    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    override fun bindView() {
        binding.tvSearch.setOnClickListener { toast("start Search .") }
        binding.vpContainer.adapter = vpAdapter
        binding.tabCategory.setupWithViewPager(binding.vpContainer)
        TabLayoutUtil.reflex(binding.tabCategory)
    }

    override fun bindData(savedInstanceState: Bundle?) {
        viewModel.apply {
            //获取分类数据 构建分类tab 及 fragment
            categoryObservable.observe(this@HomeFragment, Observer {
                it.map {
                    titles.add(it.title)
                    fragments.add(ContentListFragment())
                }
                vpAdapter.notifyDataSetChanged()
                binding.vpContainer.offscreenPageLimit = fragments.size - 1
            })

            //获取
            failedEvent.observe(this@HomeFragment, Observer {
                when (it.code) {
                    -1 -> toast("列表数据请求错误")
                }
            })
        }
    }

    override fun lazyLoad() {
        super.lazyLoad()
        viewModel.buildCategoryFragment()
    }

}
