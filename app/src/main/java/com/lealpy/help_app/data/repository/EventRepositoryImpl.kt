package com.lealpy.help_app.data.repository

import android.util.Log
import com.lealpy.help_app.data.api.EventApi
import com.lealpy.help_app.data.database.search_by_events.EventDao
import com.lealpy.help_app.data.utils.DataUtils
import com.lealpy.help_app.data.utils.DataUtils.Companion.LOG_TAG
import com.lealpy.help_app.data.utils.toEventEntities
import com.lealpy.help_app.data.utils.toEventItems
import com.lealpy.help_app.domain.model.EventItem
import com.lealpy.help_app.domain.repository.EventRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDao: EventDao,
    private val eventApi: EventApi,
    private val utils: DataUtils,
) : EventRepository {

    override fun getFromDbEventItemsByTitle(searchQuery: String): Single<List<EventItem>> {
        return eventDao.getEventEntitiesByTitle(searchQuery).map { eventEntities ->
            eventEntities.toEventItems()
        }
    }

    override fun updateEventItems(): Completable {
        return eventApi.getEventItems()
            .doOnSuccess { eventItems ->
                insertToDbEventItems(eventItems).blockingSubscribe()
            }
            .onErrorResumeNext { error ->
                Log.e(LOG_TAG, error.message.toString())
                getFromDbAllEventItems()
                    .flatMap { eventItemsFromDb ->
                        if (eventItemsFromDb.isNotEmpty()) {
                            Single.just(eventItemsFromDb)
                        } else {
                            val eventItemsFromFile =
                                utils.getItemsFromFile<List<EventItem>>(EVENT_ITEMS_FILE_NAME)
                            insertToDbEventItems(eventItemsFromFile).blockingSubscribe()
                            Single.just(eventItemsFromFile)
                        }
                    }
            }
            .flatMapCompletable {
                Completable.complete()
            }
    }

    private fun getFromDbAllEventItems(): Single<List<EventItem>> {
        return eventDao.getAllEventEntities().map { eventEntities ->
            eventEntities.toEventItems()
        }
    }

    private fun insertToDbEventItems(eventItems: List<EventItem>): Completable {
        val eventEntities = eventItems.toEventEntities()
        return eventDao.insertEventEntities(eventEntities)
    }

    companion object {
        private const val EVENT_ITEMS_FILE_NAME = "event_items.json"
    }

}
