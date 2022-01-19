package com.lealpy.simbirsoft_training.database.events

import io.reactivex.Completable
import io.reactivex.Single

class EventRepository (
    private val eventDao : EventDao
) {

    fun getEventEntitiesByTitle(searchQuery : String) : Single<List<EventEntity>> {
        return eventDao.getEventEntitiesByTitle(searchQuery)
    }

    fun insertEventEntities(eventEntities : List<EventEntity>) : Completable {
        return eventDao.insertEventEntities(eventEntities)
    }

}
