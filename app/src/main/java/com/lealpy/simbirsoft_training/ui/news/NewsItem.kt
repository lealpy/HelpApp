package com.lealpy.simbirsoft_training.ui.news

data class NewsItem (
    val id : Long,
    val image : Int,
    val title : String,
    val abbreviatedText : String,
    val date : String,
    val fundName : String,
    val address: String,
    val phone : String,
    val image2 : Int,
    val image3 : Int,
    val fullText : String,
    val isChildrenCategory : Boolean,
    val isAdultsCategory : Boolean,
    val isElderlyCategory : Boolean,
    val isAnimalsCategory : Boolean,
    val isEventsCategory : Boolean
)