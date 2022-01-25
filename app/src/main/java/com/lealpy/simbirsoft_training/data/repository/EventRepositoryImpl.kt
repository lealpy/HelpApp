package com.lealpy.simbirsoft_training.data.repository

import android.util.Log
import com.lealpy.simbirsoft_training.data.api.EventApi
import com.lealpy.simbirsoft_training.data.database.events.EventDao
import com.lealpy.simbirsoft_training.data.utils.DataUtils
import com.lealpy.simbirsoft_training.data.utils.toEventEntities
import com.lealpy.simbirsoft_training.data.utils.toEventItems
import com.lealpy.simbirsoft_training.domain.model.EventItem
import com.lealpy.simbirsoft_training.domain.repository.EventRepository
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDao : EventDao,
    private val eventApi: EventApi,
    private val utils : DataUtils
) : EventRepository {

    override fun saveToDbEventItemsFromServer() : Completable {
        return Completable.create{ emitter ->
            eventApi.getEventItems()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { eventItemsFromServer ->
                        insertToDbEventItems(eventItemsFromServer)
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                {
                                    emitter.onComplete()
                                },
                                { error ->
                                    Log.e(DataUtils.LOG_TAG, error.message.toString())
                                }
                            )
                    },
                    { error ->
                        error.message?.let { err -> Log.e(PresentationUtils.LOG_TAG, err) }
                        emitter.onError(error)
                    }
                )
        }
    }

    override fun saveToDbEventItemsFromFile() : Completable {
        return Completable.create { emitter ->
            val eventItemsFromFile = utils.getItemsFromFile<List<EventItem>>(EVENT_ITEMS_FILE_NAME)
            insertToDbEventItems(eventItemsFromFile)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        emitter.onComplete()
                    },
                    {error ->
                        Log.e(DataUtils.LOG_TAG, error.message.toString())
                    }
                )
        }
    }

    override fun getFromDbEventItemsByTitle(searchQuery : String) : Single<List<EventItem>> {
        return eventDao.getEventEntitiesByTitle(searchQuery).map { eventEntities ->
            eventEntities.toEventItems()
        }
    }

    override fun insertToDbEventItems(eventItems : List<EventItem>) : Completable {
        val eventEntities = eventItems.toEventEntities()
        return eventDao.insertEventEntities(eventEntities)
    }

    companion object {
        private const val EVENT_ITEMS_FILE_NAME = "event_items.json"
    }

}
