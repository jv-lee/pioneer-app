package com.lee.pioneer.view.recommend

import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseFragment
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentComicBinding

/**
 * @author jv.lee
 * @date 2020/8/21
 * @description
 */
class ComicFragment : BaseFragment<FragmentComicBinding, BaseViewModel>(R.layout.fragment_comic) {

    private val mAdapter by lazy { RecommendAdapter(requireContext(), ArrayList()) }

    override fun bindView() {
        binding.rvContainer.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    override fun bindData() {
        val testMall = TestRepository.testMall()
        mAdapter.updateData(testMall)
        mAdapter.notifyDataSetChanged()
    }

}