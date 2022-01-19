package com.lealpy.simbirsoft_training.database.help

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HelpDao {

    @Query("SELECT * FROM help_entities")
    fun getAllHelpEntities(): Single<List<HelpEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHelpEntities(helpEntities: List<HelpEntity>): Completable

}
