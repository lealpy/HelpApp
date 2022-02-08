package com.lealpy.simbirsoft_training.presentation.screens.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.simbirsoft_training.databinding.ItemHelpBinding
import com.lealpy.simbirsoft_training.presentation.model.HelpItemUi

class HelpItemAdapter (
    private val onItemClick: (helpItemUi : HelpItemUi) -> Unit,
): ListAdapter<HelpItemUi, HelpItemAdapter.HelpItemHolder>(DiffCallback()) {

    inner class HelpItemHolder(
        private val binding: ItemHelpBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    val helpItem = getItem(position)
                    onItemClick(helpItem)
                }
            }
        }

        fun bind(helpItemUi: HelpItemUi) {
            binding.helpItemImage.setImageBitmap(helpItemUi.image)
            binding.helpItemText.text = helpItemUi.text
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

    class DiffCallback: DiffUtil.ItemCallback<HelpItemUi>() {
        override fun areItemsTheSame(oldItemUi: HelpItemUi, newItemUi: HelpItemUi) =
            oldItemUi.id == newItemUi.id

        override fun areContentsTheSame(oldItemUi: HelpItemUi, newItemUi: HelpItemUi) =
            oldItemUi == newItemUi
    }

}
