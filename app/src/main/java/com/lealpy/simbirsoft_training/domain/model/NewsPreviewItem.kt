package com.lealpy.simbirsoft_training.domain.model

data class NewsPreviewItem (
    val id : Long,
    val imageUrl : String,
    val title : String,
    val abbreviatedText : String,
    val date : String,
    val isChildrenCategory : Boolean,
    val isAdultCategory : Boolean,
    val isElderlyCategory : Boolean,
    val isAnimalCategory : Boolean,
    val isEventCategory : Boolean
)
