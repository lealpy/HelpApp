package com.lealpy.simbirsoft_training.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.simbirsoft_training.databinding.ItemNewsBinding

class NewsItemAdapter (
    private val onItemClickListener: OnItemClickListener,
): ListAdapter<NewsItem, NewsItemAdapter.NewsItemHolder>(DiffCallback()) {

    inner class NewsItemHolder(
        private val binding: ItemNewsBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                binding.root.setOnClickListener {
                    val position = layoutPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val newsItem = getItem(position)
                        onItemClickListener.onItemClick(newsItem)
                    }
                }
            }
        }

        fun bind(newsItem: NewsItem) {
            binding.newsItemImage.setImageBitmap(newsItem.image)
            binding.newsItemTitle.text = newsItem.title
            binding.newsItemText.text = newsItem.abbreviatedText
            binding.newsItemDate.text = newsItem.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsItemHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    interface OnItemClickListener {
        fun onItemClick(newsItem: NewsItem)
    }

    class DiffCallback: DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem) =
            oldItem == newItem
    }
}
