package com.lealpy.simbirsoft_training.data.database.news

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class NewsPreviewDb (
    @PrimaryKey
    val id : Long,
    @ColumnInfo(name = "image_url")
    val imageUrl : String,
    val title : String,
    @ColumnInfo(name = "abbreviated_text")
    val abbreviatedText : String,
    val date : String,
    @ColumnInfo(name = "is_children_category")
    val isChildrenCategory : Boolean,
    @ColumnInfo(name = "is_adult_category")
    val isAdultCategory : Boolean,
    @ColumnInfo(name = "is_elderly_category")
    val isElderlyCategory : Boolean,
    @ColumnInfo(name = "is_animal_category")
    val isAnimalCategory : Boolean,
    @ColumnInfo(name = "is_event_category")
    val isEventCategory : Boolean
)
