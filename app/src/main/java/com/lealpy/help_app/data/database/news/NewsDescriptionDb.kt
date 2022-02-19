package com.lealpy.help_app.data.database.news

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class NewsDescriptionDb (
    @PrimaryKey
    val id : Long,
    @ColumnInfo(name = "image_url")
    val imageUrl : String,
    val title : String,
    @ColumnInfo(name = "abbreviated_text")
    val abbreviatedText : String,
    val date : Long,
    @ColumnInfo(name = "fund_name")
    val fundName : String,
    val address: String,
    val phone : String,
    @ColumnInfo(name = "image_2_url")
    val image2Url : String,
    @ColumnInfo(name = "image_3_url")
    val image3Url : String,
    @ColumnInfo(name = "full_text")
    val fullText : String
)
