package com.lealpy.simbirsoft_training.ui.news

data class NewsItemJson (
    val id : Long,
    val imageURL : String,
    val title : String,
    val abbreviatedText : String,
    val date : String,
    val fundName : String,
    val address: String,
    val phone : String,
    val image2URL : String,
    val image3URL : String,
    val fullText : String,
    val isChildrenCategory : Boolean,
    val isAdultsCategory : Boolean,
    val isElderlyCategory : Boolean,
    val isAnimalsCategory : Boolean,
    val isEventsCategory : Boolean
)
