package com.lealpy.simbirsoft_training.data.database.nko

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "nko_entities",
    indices = [Index("title")]
)
data class NkoEntity (
    @PrimaryKey
    val id: Long,
    val title : String
)
