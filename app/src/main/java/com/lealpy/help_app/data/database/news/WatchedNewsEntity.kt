package com.lealpy.help_app.data.database.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watched_news_entities")
data class WatchedNewsEntity(
    @PrimaryKey
    val id: Long,
)
