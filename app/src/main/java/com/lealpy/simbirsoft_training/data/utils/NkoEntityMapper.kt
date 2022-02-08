package com.lealpy.simbirsoft_training.data.utils

import com.lealpy.simbirsoft_training.data.database.search_by_nko.NkoEntity
import com.lealpy.simbirsoft_training.domain.model.NkoItem

fun List<NkoEntity>.toNkoItems() : List<NkoItem> {
    return this.map { nkoEntity ->
        NkoItem(
            id = nkoEntity.id,
            title = nkoEntity.title
        )
    }
}

fun List<NkoItem>.toNkoEntities() : List<NkoEntity> {
    return this.map { nkoItem ->
        NkoEntity(
            id = nkoItem.id,
            title = nkoItem.title
        )
    }
}
