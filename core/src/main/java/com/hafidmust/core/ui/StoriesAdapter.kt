package com.hafidmust.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafidmust.core.R
import com.hafidmust.core.databinding.ItemListStoriesBinding
import com.hafidmust.core.domain.model.Stories


class StoriesAdapter : RecyclerView.Adapter<StoriesAdapter.ListViewHolder>() {

    private var listData = ArrayList<Stories>()
    var onItemClick : ((Stories) -> Unit)? = null

    fun setData(newListData : List<Stories>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListStoriesBinding.bind(itemView)
        fun bind(data : Stories)
        {
            with(binding){
                Glide.with(itemView.context)
                    .load(data.photoUrl)
                    .into(imgItemPoster)
                tvItemName.text = data.name
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_stories, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}