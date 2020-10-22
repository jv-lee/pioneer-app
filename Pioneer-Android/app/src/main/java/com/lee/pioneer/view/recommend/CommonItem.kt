package com.lee.pioneer.view.recommend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
class CommonItem : LeeViewItem<Recommend> {
    override fun getItemLayout(): Int {
        return R.layout.item_recommend_common
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
        return entity?.type == ViewStyle.COMMON
    }

    override fun convert(holder: LeeViewHolder?, entity: Recommend?, position: Int) {
        holder?.getView<TextView>(R.id.tv_title)?.run {
            text = entity?.title
        }

        holder?.getView<RecyclerView>(R.id.rv_container)?.run {
            entity?.comics?.let {
                layoutManager = GridLayoutManager(context, 3).apply { isAutoMeasureEnabled = true }
                if (adapter == null) {
                    adapter = CommonAdapter(it)
                }
            }
        }
    }

    override fun viewRecycled(holder: LeeViewHolder?, entity: Recommend?, position: Int) {
    }

    private class CommonAdapter(val data: ArrayList<Comic>) :
        RecyclerView.Adapter<CommonAdapter.CommonViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
            return CommonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_common, null, false)
            )
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
            holder.bindView(data[position])
        }

        private class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindView(entity: Comic) {
                GlideTools.get()
                    .loadImage(entity.cover, itemView.findViewById<ImageView>(R.id.iv_cover))
                itemView.findViewById<TextView>(R.id.tv_book_name).text = entity.name
            }
        }

    }

}