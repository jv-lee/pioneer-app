package com.lee.pioneer.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.library.base.BaseNavigationFragment
import com.lee.library.utils.LogUtil
import com.lee.pioneer.LaunchActivity
import com.lee.pioneer.R
import com.lee.pioneer.databinding.FragmentContentListBinding
import com.lee.pioneer.view.adapter.ContentAdapter
import com.lee.pioneer.viewmodel.ContentListViewModel

private const val ARG_PARAM_TYPE = "arg_param_type"

/**
 * @author jv.lee
 * TODO 内容列表页面
 */
class ContentListFragment :
    BaseNavigationFragment<FragmentContentListBinding, ContentListViewModel>(
        R.layout.fragment_content_list,
        ContentListViewModel::class.java
    ) {
    private var type: String? = null
    private val mAdapter by lazy { ContentAdapter(context!!, ArrayList()) }

    companion object {
        @JvmStatic
        fun newInstance(type: String) = ContentListFragment().apply {
            arguments = Bundle().apply { putString(ARG_PARAM_TYPE, type) }
        }
    }

    override fun intentParams(arguments: Bundle?, savedInstanceState: Bundle?) {
        super.intentParams(arguments, savedInstanceState)
        arguments?.let { type = it.getString(ARG_PARAM_TYPE) }
    }

    override fun bindView() {
        binding.rvContainer.adapter = mAdapter
        binding.rvContainer.layoutManager = LinearLayoutManager(context)
    }

    override fun bindData() {
        viewModel.apply {
            contentListObservable.observe(this@ContentListFragment, Observer {
//                mAdapter.addData(it.data)
//                mAdapter.notifyDataSetChanged()
                toast("dataSize:${it.data.size}")
            })
            failedEvent.observe(this@ContentListFragment, Observer { it ->
                it.message?.let { toast(it) }
            })
        }

        type?.let {
            //是否到最后一页 (page * page_count) > total_counts
            viewModel.loadListData(it, false)
        }

    }


}
