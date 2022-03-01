package com.lealpy.help_app.presentation.model

import android.graphics.Bitmap

data class NewsPreviewItemUi(
    val id: Long,
    val image: Bitmap?,
    val title: String,
    val abbreviatedText: String,
    val date: String,
    val isChildrenCategory: Boolean,
    val isAdultCategory: Boolean,
    val isElderlyCategory: Boolean,
    val isAnimalCategory: Boolean,
    val isEventCategory: Boolean,
)
