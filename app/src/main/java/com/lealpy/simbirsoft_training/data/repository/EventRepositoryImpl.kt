package com.lealpy.simbirsoft_training.data.repository

import com.lealpy.simbirsoft_training.data.database.events.EventDao
import com.lealpy.simbirsoft_training.data.database.events.EventEntity
import com.lealpy.simbirsoft_training.domain.repository.EventRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDao : EventDao
) : EventRepository {

    fun getEventEntitiesByTitle(searchQuery : String) : Single<List<EventEntity>> {
        return eventDao.getEventEntitiesByTitle(searchQuery)
    }

    fun insertEventEntities(eventEntities : List<EventEntity>) : Completable {
        return eventDao.insertEventEntities(eventEntities)
    }

}
