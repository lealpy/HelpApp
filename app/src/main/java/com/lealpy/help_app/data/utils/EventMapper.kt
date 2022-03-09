package com.lealpy.help_app.data.utils

import com.lealpy.help_app.data.database.search_by_events.EventEntity
import com.lealpy.help_app.domain.model.EventItem

fun List<EventEntity>.toEventItems(): List<EventItem> {
    return this.map { eventEntity ->
        EventItem(
            id = eventEntity.id,
            title = eventEntity.title,
            date = eventEntity.date
        )
    }
}

fun List<EventItem>.toEventEntities(): List<EventEntity> {
    return this.map { eventItem ->
        EventEntity(
            id = eventItem.id,
            title = eventItem.title,
            date = eventItem.date
        )
    }
}
