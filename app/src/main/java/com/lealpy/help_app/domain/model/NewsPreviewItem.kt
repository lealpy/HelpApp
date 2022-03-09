package com.lealpy.help_app.domain.model

data class NewsPreviewItem(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val abbreviatedText: String,
    val date: Long,
    val isChildrenCategory: Boolean,
    val isAdultCategory: Boolean,
    val isElderlyCategory: Boolean,
    val isAnimalCategory: Boolean,
    val isEventCategory: Boolean,
)
