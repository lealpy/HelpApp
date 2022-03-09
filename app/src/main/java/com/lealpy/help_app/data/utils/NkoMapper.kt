package com.lealpy.help_app.data.utils

import com.lealpy.help_app.data.database.search_by_nko.NkoEntity
import com.lealpy.help_app.domain.model.NkoItem

fun List<NkoEntity>.toNkoItems(): List<NkoItem> {
    return this.map { nkoEntity ->
        NkoItem(
            id = nkoEntity.id,
            title = nkoEntity.title
        )
    }
}

fun List<NkoItem>.toNkoEntities(): List<NkoEntity> {
    return this.map { nkoItem ->
        NkoEntity(
            id = nkoItem.id,
            title = nkoItem.title
        )
    }
}
