package com.lee.pioneer.recommend.widget

import com.lee.library.R
import com.lee.pioneer.recommend.R as RR
import com.lee.library.adapter.listener.LoadResource

/**
 * @author jv.lee
 * @date 2020/4/13
 * @description 自定义推荐栏加载样式
 */
class RecommendLoadResource : LoadResource {
    override fun pageLayoutId(): Int {
        return RR.layout.layout_recommend_page_load
    }

    override fun pageLoadingId(): Int {
        return RR.id.const_page_loading
    }

    override fun pageEmptyId(): Int {
        return RR.id.const_page_empty
    }

    override fun pageErrorId(): Int {
        return RR.id.const_page_error
    }

    override fun pageReloadId(): Int {
        return RR.id.btn_restart
    }

    override fun itemLayoutId(): Int {
        return R.layout.layout_item_load
    }

    override fun itemLoadMoreId(): Int {
        return R.id.const_item_loadMore
    }

    override fun itemLoadEndId(): Int {
        return R.id.const_item_loadEnd
    }

    override fun itemLoadErrorId(): Int {
        return R.id.const_item_loadError
    }

    override fun itemReloadId(): Int {
        return R.id.tv_error_text
    }

}