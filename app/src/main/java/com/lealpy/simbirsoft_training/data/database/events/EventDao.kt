package com.lealpy.simbirsoft_training.data.database.events

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EventDao {

    @Query("SELECT * FROM event_entities WHERE title LIKE '%' || :searchQuery || '%'")
    fun getEventEntitiesByTitle(searchQuery : String) : Single<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEventEntities(eventEntities : List<EventEntity>) : Completable

}
