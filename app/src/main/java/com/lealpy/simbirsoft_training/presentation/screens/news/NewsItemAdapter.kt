package com.lealpy.simbirsoft_training.presentation.screens.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.simbirsoft_training.databinding.ItemNewsBinding
import com.lealpy.simbirsoft_training.presentation.model.NewsItemUi

class NewsItemAdapter (
    private val onItemClick : (newsItemUi : NewsItemUi) -> Unit,
): ListAdapter<NewsItemUi, NewsItemAdapter.NewsItemHolder>(DiffCallback()) {

    inner class NewsItemHolder(
        private val binding: ItemNewsBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    val newsItem = getItem(position)
                    onItemClick(newsItem)
                }
            }
        }

        fun bind(newsItemUi: NewsItemUi) {
            binding.newsItemImage.setImageBitmap(newsItemUi.image)
            binding.newsItemTitle.text = newsItemUi.title
            binding.newsItemText.text = newsItemUi.abbreviatedText
            binding.newsItemDate.text = newsItemUi.date
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

    class DiffCallback: DiffUtil.ItemCallback<NewsItemUi>() {
        override fun areItemsTheSame(oldItemUi: NewsItemUi, newItemUi: NewsItemUi) =
            oldItemUi.id == newItemUi.id

        override fun areContentsTheSame(oldItemUi: NewsItemUi, newItemUi: NewsItemUi) =
            oldItemUi == newItemUi
    }

}
