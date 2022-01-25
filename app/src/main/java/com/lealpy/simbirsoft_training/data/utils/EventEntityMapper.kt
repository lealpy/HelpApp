package com.lealpy.simbirsoft_training.data.utils

import com.lealpy.simbirsoft_training.data.database.events.EventEntity
import com.lealpy.simbirsoft_training.domain.model.EventItem

fun List<EventEntity>.toEventItems() : List<EventItem> {
    return this.map { eventEntity ->
        EventItem(
            id = eventEntity.id,
            title = eventEntity.title,
            date = eventEntity.date
        )
    }
}

fun List<EventItem>.toEventEntities() : List<EventEntity> {
    return this.map { eventItem ->
        EventEntity(
            id = eventItem.id,
            title = eventItem.title,
            date = eventItem.date
        )
    }
}

