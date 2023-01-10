package com.hafidmust.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hafidmust.core.R
import com.hafidmust.core.databinding.ItemListStoriesBinding
import com.hafidmust.core.domain.model.Stories

class MadeStoriesAdapter(private val clickListener : IClickListener) : ListAdapter<Stories , MadeStoriesAdapter.ViewHolder>(StoriesComparator()) {


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemListStoriesBinding.bind(itemView)
        fun bind(data : Stories){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.photoUrl)
                    .into(imgItemPoster)
                tvItemName.text = data.name
                root.setOnClickListener {
                    clickListener.onClick(data)
                }
            }
        }
    }

    class StoriesComparator : DiffUtil.ItemCallback<Stories>() {
        override fun areItemsTheSame(oldItem: Stories, newItem: Stories): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Stories, newItem: Stories): Boolean {
            return oldItem.name == newItem.name
        }

    }

    interface IClickListener {
        fun onClick(stories: Stories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_stories, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}