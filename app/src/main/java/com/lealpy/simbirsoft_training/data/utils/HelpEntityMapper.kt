package com.lealpy.simbirsoft_training.data.utils

import com.lealpy.simbirsoft_training.data.database.help.HelpEntity
import com.lealpy.simbirsoft_training.domain.model.HelpItem

fun List<HelpEntity>.toHelpItems() : List<HelpItem> {
    return this.map { helpEntity ->
        HelpItem(
            id = helpEntity.id,
            imageUrl = helpEntity.imageUrl,
            text = helpEntity.text
        )
    }
}

fun List<HelpItem>.toHelpEntities() : List<HelpEntity> {
    return this.map { helpItem ->
        HelpEntity(
            id = helpItem.id,
            imageUrl = helpItem.imageUrl,
            text = helpItem.text
        )
    }
}
