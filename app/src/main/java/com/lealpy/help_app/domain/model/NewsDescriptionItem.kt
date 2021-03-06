package com.lealpy.help_app.domain.model

data class NewsDescriptionItem(
    val id: Long,
    val imageUrl: String,
    val title: String,
    val abbreviatedText: String,
    val date: Long,
    val fundName: String,
    val address: String,
    val phone: String,
    val image2Url: String,
    val image3Url: String,
    val fullText: String,
)
