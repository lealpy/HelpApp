package com.lealpy.simbirsoft_training.domain.use_cases.nko

import android.util.Log
import com.lealpy.simbirsoft_training.data.utils.DataUtils.Companion.LOG_TAG
import com.lealpy.simbirsoft_training.domain.repository.NkoRepository
import io.reactivex.Completable
import javax.inject.Inject

class SaveToDbNkoItemsUseCase @Inject constructor(
    private val repository: NkoRepository
) {

    fun execute() : Completable {
        return Completable.create { emitter ->
            repository.saveToDbNkoItemsFromServer()
                .subscribe(
                    {
                        emitter.onComplete()
                    },
                    {
                        repository.getFromDbAllNkoItems()
                            .subscribe(
                                { nkoItems ->
                                    if(nkoItems.isNullOrEmpty()) {
                                        repository.saveToDbNkoItemsFromFile()
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
