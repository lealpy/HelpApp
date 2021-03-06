package com.lealpy.help_app.data.database.search_by_nko

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface NkoDao {

    @Query("SELECT * FROM nko_entities WHERE title LIKE '%' || :searchQuery || '%'")
    fun getNkoEntitiesByTitle(searchQuery: String): Single<List<NkoEntity>>

    @Query("SELECT * FROM nko_entities")
    fun getAllNkoEntities(): Single<List<NkoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNkoEntities(nkoEntities: List<NkoEntity>): Completable

}