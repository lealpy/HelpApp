package com.lealpy.simbirsoft_training.data.database.events

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventDao : EventDao
) {

    fun getEventEntitiesByTitle(searchQuery : String) : Single<List<EventEntity>> {
        return eventDao.getEventEntitiesByTitle(searchQuery)
    }

    fun insertEventEntities(eventEntities : List<EventEntity>) : Completable {
        return eventDao.insertEventEntities(eventEntities)
    }

}
