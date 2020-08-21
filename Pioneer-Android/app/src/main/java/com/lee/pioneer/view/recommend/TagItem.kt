package com.lee.pioneer.view.recommend

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee.library.adapter.LeeViewHolder
import com.lee.library.adapter.listener.LeeViewItem
import com.lee.pioneer.R

/**
 * @author jv.lee
 * @date 2020/8/21
 * @description
 */
class TagItem :LeeViewItem<Recommend>{
    override fun getItemLayout(): Int {
        return R.layout.item_recommend_tag
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
        return entity?.type == ViewStyle.TAG
    }

    override fun convert(holder: LeeViewHolder?, entity: Recommend?, position: Int) {
        holder?.getView<TextView>(R.id.tv_title)?.run {
            text = entity?.title
        }
        holder?.getView<RecyclerView>(R.id.rv_container)?.run {
            entity?.tags?.let {
                layoutManager = GridLayoutManager(context, 4)
                adapter = TagAdapter(it)
            }
        }
    }

    override fun viewRecycled(holder: LeeViewHolder?, entity: Recommend?, position: Int) {
        
    }

    private class TagAdapter(val data:ArrayList<Tag>) :
        RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
            return TagViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tag,null,false))
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
            holder.bindView(data[position])
        }

        private class TagViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun bindView(entity: Tag) {
                itemView.findViewById<TextView>(R.id.tv_tag)?.run {
                    text = entity.text
                }
            }
        }

    }

}