package com.lee.pioneer.view.recommend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.pioneer.R
import com.lee.pioneer.tools.GlideTools

/**
 * @author jv.lee
 * @date 2020/8/21
 * @description
 */
class RisingItem :LeeViewItem<Recommend>{
    override fun getItemLayout(): Int {
        return R.layout.item_recommend_rising
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
        return entity?.type == ViewStyle.RISING
    }

    override fun convert(holder: LeeViewHolder?, entity: Recommend?, position: Int) {
        holder?.getView<TextView>(R.id.tv_title)?.run {
            text = entity?.title
        }
        holder?.getView<RecyclerView>(R.id.rv_container)?.run {
            entity?.comics?.let {
                layoutManager = GridLayoutManager(context, 2).apply { isAutoMeasureEnabled = true }
                adapter = RisingAdapter(it)
            }
        }
    }

    override fun viewRecycled(holder: LeeViewHolder?, entity: Recommend?, position: Int) {

    }

    private class RisingAdapter(val data:ArrayList<Comic>) :
        RecyclerView.Adapter<RisingAdapter.RisingViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RisingViewHolder {
            return RisingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rising,null,false))
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: RisingViewHolder, position: Int) {
            holder.bindView(data[position])
        }

        private class RisingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun bindView(entity: Comic) {
                    val ivCover = itemView.findViewById<ImageView>(R.id.iv_cover)
                    val tvRankTips = itemView.findViewById<TextView>(R.id.tv_rank_tips)
                    val tvBookName = itemView.findViewById<TextView>(R.id.tv_book_name)
                    val tvBookTag = itemView.findViewById<TextView>(R.id.tv_book_tag)
                    val tvRankNumber = itemView.findViewById<TextView>(R.id.iv_rank_number)
                    entity?.run {
                        GlideTools.get().loadImage(cover,ivCover)
                        tvRankTips.text = "Top1"
                        tvRankNumber.text = rising.toString()
                        tvBookName.text = name
                        tvBookTag.text = tags[0].text
                    }
            }
        }

    }

}