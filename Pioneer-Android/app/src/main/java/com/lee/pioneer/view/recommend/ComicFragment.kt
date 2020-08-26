package com.lee.pioneer.view.recommend

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee.library.base.BaseFragment
import com.lee.library.base.BaseNavigationFragment
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
class ComicFragment : BaseNavigationFragment<FragmentComicBinding, ComicViewModel>(R.layout.fragment_comic) {

    private var count = 1
    private val mAdapter by lazy { RecommendAdapter(requireContext(), ArrayList()) }

    override fun bindView() {
        binding.rvContainer.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter.proxy
        }

        mAdapter.run {
            initStatusView()
            pageLoading()
            setAutoLoadMoreListener {
                viewModel.getData()
            }
        }

    }

    override fun bindData() {
        viewModel.dataObservable.observe(this@ComicFragment, Observer {
            mAdapter.updateData(it)
            when {
                count == 1 -> {
                    mAdapter.pageCompleted()
                }
                count < 10 -> {
                    mAdapter.loadMoreCompleted()
                }
                count >= 10 -> {
                    mAdapter.loadMoreEnd()
                }
            }
            count++
        })
    }

    override fun lazyLoad() {
        viewModel.getData()
        //提前绘制 优化多类型掉帧问题
//        if (mAdapter.itemCount > 4) {
//            binding.rvContainer.smoothScrollToPosition(4)
//            binding.rvContainer.smoothScrollToPosition(0)
//        }
    }
}