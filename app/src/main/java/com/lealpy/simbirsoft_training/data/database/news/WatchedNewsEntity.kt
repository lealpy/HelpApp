package com.lealpy.simbirsoft_training.data.database.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "id_watched_news")
data class WatchedNewsEntity (
    @PrimaryKey
    val watchedNewsId : Long
)
