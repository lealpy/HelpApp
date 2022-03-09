package com.lealpy.help_app.data.database.help

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface HelpDao {

    @Query("SELECT * FROM help_entities")
    fun getAllHelpEntities(): Single<List<HelpEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHelpEntities(helpEntities: List<HelpEntity>): Completable

}
