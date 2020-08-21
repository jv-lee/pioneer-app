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
class IconItem :LeeViewItem<Recommend>{
    override fun getItemLayout(): Int {
        return R.layout.item_recommend_icon
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
        return entity?.type == ViewStyle.ICON
    }

    override fun convert(holder: LeeViewHolder?, entity: Recommend?, position: Int) {
        holder?.getView<RecyclerView>(R.id.rv_container)?.run {
            entity?.banners?.let {
                layoutManager = GridLayoutManager(context, it.size)
                adapter = IconGridAdapter(it)
            }

        }
    }

    override fun viewRecycled(holder: LeeViewHolder?, entity: Recommend?, position: Int) {

    }

    private class IconGridAdapter(val data:ArrayList<Banner>) :
        RecyclerView.Adapter<IconGridAdapter.IconGridViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconGridViewHolder {
            return IconGridViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_icon,null,false))
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: IconGridViewHolder, position: Int) {
            holder.bindView(data[position])
        }

        private class IconGridViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
            fun bindView(entity: Banner) {
                GlideTools.get().loadImage(entity.url,itemView.findViewById<ImageView>(R.id.iv_cover))
                itemView.findViewById<TextView>(R.id.tv_category_name).text = entity.title
            }
        }

    }

}