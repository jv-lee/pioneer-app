package com.lee.pioneer.view.adapter.resource

import com.lee.library.R
import com.lee.library.adapter.listener.LoadResource

/**
 * @author jv.lee
 * @date 2020/4/13
 * @description 自定义推荐栏加载样式
 */
class RecommendLoadResource : LoadResource {
    override fun pageLayoutId(): Int {
        return com.lee.pioneer.R.layout.layout_page_load
    }

    override fun pageLoadingId(): Int {
        return com.lee.pioneer.R.id.const_page_loading
    }

    override fun pageEmptyId(): Int {
        return com.lee.pioneer.R.id.const_page_empty
    }

    override fun pageErrorId(): Int {
        return com.lee.pioneer.R.id.const_page_error
    }

    override fun pageReloadId(): Int {
        return com.lee.pioneer.R.id.btn_restart
    }

    override fun itemLayoutId(): Int {
        return R.layout.widget_item_load
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