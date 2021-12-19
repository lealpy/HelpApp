package com.lealpy.simbirsoft_training.ui.news

data class NewsItem (
    val id : Long,
    val image : Int,
    val title : String,
    val text : String,
    val dateText : String,
    val isChildrenCategory : Boolean,
    val isAdultsCategory : Boolean,
    val isElderlyCategory : Boolean,
    val isAnimalsCategory : Boolean,
    val isEventsCategory : Boolean
)