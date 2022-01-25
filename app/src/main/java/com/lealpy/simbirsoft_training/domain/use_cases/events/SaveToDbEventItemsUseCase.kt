package com.lealpy.simbirsoft_training.domain.use_cases.events

import com.lealpy.simbirsoft_training.domain.repository.EventRepository
import io.reactivex.Completable
import javax.inject.Inject

class SaveToDbEventItemsUseCase @Inject constructor(
    private val repository: EventRepository
){

    fun execute() : Completable {
        return Completable.create { emitter ->
            repository.saveToDbEventItemsFromServer()
                .subscribe(
                    {
                        emitter.onComplete()
                    },
                    {
                        repository.saveToDbEventItemsFromFile()
                            .subscribe {
                                emitter.onComplete()
                            }
                    }
                )
        }
    }

}
