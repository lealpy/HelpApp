package com.lealpy.simbirsoft_training.presentation.model

import android.graphics.Bitmap
/**
 * Nullable-типы применяются к переменным для NewsDescriptionFragment,
 * которые не используются в NewsFragment
 * Цель - не загружать лишние данные
 */

data class NewsItemUi (
    val id : Long,
    val image : Bitmap,
    val title : String,
    val abbreviatedText : String,
    val date : String,
    val fundName : String?,
    val address: String?,
    val phone : String?,
    val image2 : Bitmap?,
    val image3 : Bitmap?,
    val fullText : String?,
    val isChildrenCategory : Boolean,
    val isAdultCategory : Boolean,
    val isElderlyCategory : Boolean,
    val isAnimalCategory : Boolean,
    val isEventCategory : Boolean
)
