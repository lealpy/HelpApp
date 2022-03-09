package com.lealpy.help_app.presentation.model

import android.graphics.Bitmap

data class NewsDescriptionItemUi(
    val id: Long,
    val image: Bitmap?,
    val title: String,
    val abbreviatedText: String,
    val date: String,
    val fundName: String,
    val address: String,
    val phone: String,
    val image2: Bitmap?,
    val image3: Bitmap?,
    val fullText: String,
)
