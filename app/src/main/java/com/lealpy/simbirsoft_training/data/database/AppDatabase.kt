package com.lealpy.simbirsoft_training.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lealpy.simbirsoft_training.data.database.search_by_events.EventDao
import com.lealpy.simbirsoft_training.data.database.search_by_events.EventEntity
import com.lealpy.simbirsoft_training.data.database.help.HelpDao
import com.lealpy.simbirsoft_training.data.database.help.HelpEntity
import com.lealpy.simbirsoft_training.data.database.news.WatchedNewsEntity
import com.lealpy.simbirsoft_training.data.database.news.NewsDao
import com.lealpy.simbirsoft_training.data.database.news.NewsEntity
import com.lealpy.simbirsoft_training.data.database.search_by_nko.NkoDao
import com.lealpy.simbirsoft_training.data.database.search_by_nko.NkoEntity

@Database(
    version = 1,
    entities = [
        HelpEntity::class,
        EventEntity::class,
        NkoEntity::class,
        NewsEntity::class,
        WatchedNewsEntity::class
    ],
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun helpDao(): HelpDao
    abstract fun eventDao() : EventDao
    abstract fun nkoDao() : NkoDao
    abstract fun newsDao() : NewsDao
}
