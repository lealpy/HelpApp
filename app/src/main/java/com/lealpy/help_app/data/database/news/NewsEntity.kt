package com.lealpy.help_app.data.database.news

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_entities")
data class NewsEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val title: String,
    @ColumnInfo(name = "abbreviated_text")
    val abbreviatedText: String,
    val date: Long,
    @ColumnInfo(name = "fund_name")
    val fundName: String,
    val address: String,
    val phone: String,
    @ColumnInfo(name = "image_2_url")
    val image2Url: String,
    @ColumnInfo(name = "image_3_url")
    val image3Url: String,
    @ColumnInfo(name = "full_text")
    val fullText: String,
    @ColumnInfo(name = "is_children_category")
    val isChildrenCategory: Boolean,
    @ColumnInfo(name = "is_adult_category")
    val isAdultCategory: Boolean,
    @ColumnInfo(name = "is_elderly_category")
    val isElderlyCategory: Boolean,
    @ColumnInfo(name = "is_animal_category")
    val isAnimalCategory: Boolean,
    @ColumnInfo(name = "is_event_category")
    val isEventCategory: Boolean,
)
