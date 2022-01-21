package com.lealpy.simbirsoft_training.ui.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.simbirsoft_training.databinding.ItemHelpBinding

class HelpItemAdapter (
    private val onItemClickListener: OnItemClickListener,
): ListAdapter<HelpItem, HelpItemAdapter.HelpItemHolder>(DiffCallback()) {

    inner class HelpItemHolder(
        private val binding: ItemHelpBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                binding.root.setOnClickListener {
                    val position = layoutPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val helpItem = getItem(position)
                        onItemClickListener.onItemClick(helpItem)
                    }
                }
            }
        }

        fun bind(helpItem: HelpItem) {
            binding.helpItemImage.setImageBitmap(helpItem.image)
            binding.helpItemText.text = helpItem.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpItemHolder {
        val binding = ItemHelpBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HelpItemHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    interface OnItemClickListener {
        fun onItemClick(helpItem: HelpItem)
    }

    class DiffCallback: DiffUtil.ItemCallback<HelpItem>() {
        override fun areItemsTheSame(oldItem: HelpItem, newItem: HelpItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HelpItem, newItem: HelpItem) =
            oldItem == newItem
    }
}
