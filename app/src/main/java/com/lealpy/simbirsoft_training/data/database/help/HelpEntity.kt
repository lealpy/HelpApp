package com.lealpy.simbirsoft_training.data.database.help

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "help_entities" )
data class HelpEntity (
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val text: String
)
