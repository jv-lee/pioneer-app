package com.lee.pioneer.view.recommend

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee.library.base.BaseFragment
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.utils.LogUtil
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentComicBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            adapter = mAdapter.proxy

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    LogUtil.i("itemPosition:${layoutManager.findFirstVisibleItemPosition()}")
                }
            })
        }

        mAdapter.run {
            initStatusView()
            pageLoading()
            setAutoLoadMoreListener {
                launch {
                    val testMall = withContext(Dispatchers.IO) {
                        delay(3000)
                        TestRepository.testMall()
                    }
                    mAdapter.addData(testMall)
                    loadMoreCompleted()
                }

            }
        }

    }

    override fun bindData() {

    }

    override fun lazyLoad() {
        val testMall = TestRepository.testMall()
        mAdapter.updateData(testMall)
        mAdapter.notifyDataSetChanged()
        mAdapter.pageCompleted()
        //提前绘制 优化多类型掉帧问题
        if (mAdapter.itemCount > 4) {
            binding.rvContainer.smoothScrollToPosition(4)
            binding.rvContainer.smoothScrollToPosition(0)
        }
    }
}