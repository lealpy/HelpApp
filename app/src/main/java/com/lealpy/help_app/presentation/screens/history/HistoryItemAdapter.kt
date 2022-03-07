package com.lealpy.help_app.presentation.screens.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.help_app.databinding.ItemHistoryBinding
import com.lealpy.help_app.presentation.model.DonationHistoryItemUi

class HistoryItemAdapter() :
    ListAdapter<DonationHistoryItemUi, HistoryItemAdapter.HistoryItemHolder>(DiffCallback()) {

    inner class HistoryItemHolder(
        private val binding: ItemHistoryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(donationHistoryItemUi: DonationHistoryItemUi) {
            binding.newsTitle.text = donationHistoryItemUi.newsTitle
            binding.amount.text = donationHistoryItemUi.donationAmount
            binding.date.text = donationHistoryItemUi.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoryItemHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<DonationHistoryItemUi>() {
        override fun areItemsTheSame(
            oldItem: DonationHistoryItemUi,
            newItem: DonationHistoryItemUi,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DonationHistoryItemUi,
            newItem: DonationHistoryItemUi,
        ): Boolean {
            return oldItem == newItem
        }
    }

}
