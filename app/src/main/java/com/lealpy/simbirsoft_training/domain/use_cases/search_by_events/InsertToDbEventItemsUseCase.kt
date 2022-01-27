package com.lealpy.simbirsoft_training.domain.use_cases.search_by_events

import android.util.Log
import com.lealpy.simbirsoft_training.data.utils.DataUtils.Companion.LOG_TAG
import com.lealpy.simbirsoft_training.domain.repository.EventRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertToDbEventItemsUseCase @Inject constructor(
    private val repository: EventRepository
){

    fun execute() : Completable {
        return Completable.create { emitter ->
            repository.insertToDbEventItemsFromServer()
                .subscribe(
                    {
                        emitter.onComplete()
                    },
                    {
                        repository.getFromDbAllEventItems()
                            .subscribe(
                                { eventItems ->
                                    if(eventItems.isNullOrEmpty()) {
                                        repository.insertToDbEventItemsFromFile()
                                            .subscribe {
                                                emitter.onComplete()
                                            }
                                    } else {
                                        emitter.onComplete()
                                    }
                                },
                                { error ->
                                    Log.e(LOG_TAG, error.message.toString())
                                }
                            )
                    }
                )
        }
    }

}
