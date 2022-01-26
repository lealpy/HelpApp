package com.lealpy.simbirsoft_training.presentation.screens.news.news_preview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.simbirsoft_training.databinding.ItemNewsBinding
import com.lealpy.simbirsoft_training.presentation.model.NewsPreviewItemUi

class NewsPreviewItemAdapter (
    private val onItemClick : (newsPreviewItemUi : NewsPreviewItemUi) -> Unit,
): ListAdapter<NewsPreviewItemUi, NewsPreviewItemAdapter.NewsItemHolder>(DiffCallback()) {

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

        fun bind(newsPreviewItemUi: NewsPreviewItemUi) {
            binding.newsItemImage.setImageBitmap(newsPreviewItemUi.image)
            binding.newsItemTitle.text = newsPreviewItemUi.title
            binding.newsItemText.text = newsPreviewItemUi.abbreviatedText
            binding.newsItemDate.text = newsPreviewItemUi.date
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

    class DiffCallback: DiffUtil.ItemCallback<NewsPreviewItemUi>() {
        override fun areItemsTheSame(oldItemUi: NewsPreviewItemUi, newItemUi: NewsPreviewItemUi) =
            oldItemUi.id == newItemUi.id

        override fun areContentsTheSame(oldItemUi: NewsPreviewItemUi, newItemUi: NewsPreviewItemUi) =
            oldItemUi == newItemUi
    }

}
