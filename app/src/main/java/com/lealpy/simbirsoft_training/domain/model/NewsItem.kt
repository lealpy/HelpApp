package com.lealpy.simbirsoft_training.domain.model

data class NewsItem (
    val id : Long,
    val imageUrl : String,
    val title : String,
    val abbreviatedText : String,
    val date : String,
    val fundName : String,
    val address: String,
    val phone : String,
    val image2Url : String,
    val image3Url : String,
    val fullText : String,
    val isChildrenCategory : Boolean,
    val isAdultsCategory : Boolean,
    val isElderlyCategory : Boolean,
    val isAnimalsCategory : Boolean,
    val isEventsCategory : Boolean
)
