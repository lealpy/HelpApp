package com.lealpy.simbirsoft_training.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lealpy.simbirsoft_training.data.database.events.EventDao
import com.lealpy.simbirsoft_training.data.database.events.EventEntity
import com.lealpy.simbirsoft_training.data.database.help.HelpDao
import com.lealpy.simbirsoft_training.data.database.help.HelpEntity
import com.lealpy.simbirsoft_training.data.database.nko.NkoDao
import com.lealpy.simbirsoft_training.data.database.nko.NkoEntity

@Database(
    version = 1,
    entities = [
        HelpEntity::class,
        EventEntity::class,
        NkoEntity::class
    ],
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun helpDao(): HelpDao
    abstract fun eventDao() : EventDao
    abstract fun nkoDao() : NkoDao
}
