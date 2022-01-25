package com.lealpy.simbirsoft_training.presentation.screens.search.search_by_nko

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.simbirsoft_training.databinding.ItemNkoBinding
import com.lealpy.simbirsoft_training.domain.model.NkoItem

class NkoItemAdapter (
    private val onItemClick : (nkoItem : NkoItem) -> Unit,
): ListAdapter<NkoItem, NkoItemAdapter.NkoItemHolder>(DiffCallback()) {

    inner class NkoItemHolder(
        private val binding: ItemNkoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    val nkoItem = getItem(position)
                    onItemClick(nkoItem)
                }
            }

        }

        fun bind(nkoItem: NkoItem) {
            binding.nkoName.text = nkoItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NkoItemHolder {
        val binding = ItemNkoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NkoItemHolder(binding)
    }

    override fun onBindViewHolder(holder: NkoItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback: DiffUtil.ItemCallback<NkoItem>() {
        override fun areItemsTheSame(oldItem: NkoItem, newItem: NkoItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NkoItem, newItem: NkoItem) =
            oldItem == newItem
    }

}
