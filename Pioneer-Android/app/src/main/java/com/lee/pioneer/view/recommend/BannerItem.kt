package com.lee.pioneer.view.recommend

import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.library.widget.banner.MZBannerView
import com.lee.pioneer.R

/**
 * @author jv.lee
 * @date 2020/8/21
 * @description
 */
class BannerItem :LeeViewItem<Recommend>{
    override fun getItemLayout(): Int {
        return R.layout.item_recommend_banner
    }

    override fun openClick(): Boolean {
        return true
    }

    override fun openShake(): Boolean {
        return true
    }

    override fun openRecycler(): Boolean {
        return false
    }

    override fun isItemView(entity: Recommend?, position: Int): Boolean {
        return entity?.type == ViewStyle.BANNER
    }

    override fun convert(holder: LeeViewHolder?, entity: Recommend?, position: Int) {
        holder?.getView<MZBannerView<Banner>>(R.id.banner)?.run {
            setDelayedTime(5000)
            setPages(entity?.banners) { BannerViewHolder() }
            start()
        }
    }

    override fun viewRecycled(holder: LeeViewHolder?, entity: Recommend?, position: Int) {

    }

}