package com.lealpy.help_app.presentation.screens.search.search_by_events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.help_app.databinding.ItemEventBinding
import com.lealpy.help_app.domain.model.EventItem

class EventItemAdapter (
    private val onItemClick : (eventItem : EventItem) -> Unit,
): ListAdapter<EventItem, EventItemAdapter.EventItemHolder>(DiffCallback()) {

    inner class EventItemHolder(
        private val binding: ItemEventBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    val eventItem = getItem(position)
                    onItemClick(eventItem)
                }
            }
        }

        fun bind(eventItem: EventItem) {
            binding.eventTitle.text = eventItem.title
            binding.eventDate.text = eventItem.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventItemHolder(binding)
    }

    override fun onBindViewHolder(holder: EventItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback: DiffUtil.ItemCallback<EventItem>() {
        override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem) =
            oldItem == newItem
    }

}
