package com.lealpy.simbirsoft_training.domain.use_cases.help

import android.util.Log
import com.lealpy.simbirsoft_training.data.utils.DataUtils
import com.lealpy.simbirsoft_training.domain.repository.HelpRepository
import io.reactivex.Completable
import javax.inject.Inject

class SaveToDbHelpItemsUseCase @Inject constructor(
    private val repository: HelpRepository
) {

    fun execute() : Completable {
        return Completable.create { emitter ->
            repository.saveToDbHelpItemsFromServer()
                .subscribe(
                    {
                        emitter.onComplete()
                    },
                    {
                        repository.getFromDbAllHelpItems()
                            .subscribe(
                                { helpItems ->
                                    if(helpItems.isNullOrEmpty()) {
                                        repository.saveToDbHelpItemsFromFile()
                                            .subscribe{
                                                emitter.onComplete()
                                            }
                                    } else {
                                        emitter.onComplete()
                                    }
                                },
                                { error ->
                                    Log.e(DataUtils.LOG_TAG, error.message.toString())
                                }
                            )

                        repository.saveToDbHelpItemsFromFile()
                            .subscribe {
                                emitter.onComplete()
                            }
                    }
                )
        }
    }

}
