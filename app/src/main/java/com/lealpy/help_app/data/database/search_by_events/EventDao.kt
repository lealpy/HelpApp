package com.lealpy.help_app.data.database.search_by_events

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface EventDao {

    @Query("SELECT * FROM event_entities WHERE title LIKE '%' || :searchQuery || '%'")
    fun getEventEntitiesByTitle(searchQuery : String) : Single<List<EventEntity>>

    @Query("SELECT * FROM event_entities")
    fun getAllEventEntities(): Single<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEventEntities(eventEntities : List<EventEntity>) : Completable

}
