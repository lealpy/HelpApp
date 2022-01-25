package com.lealpy.simbirsoft_training.domain.use_cases.help

import com.lealpy.simbirsoft_training.domain.repository.HelpRepository
import io.reactivex.Completable
import javax.inject.Inject

class SaveToDbHelpItemsUseCase @Inject constructor(
    private val repository: HelpRepository
) {

    fun execute() : Completable {
        return Completable.create { emitter ->
            repository.saveToDbHelpItemsFromServer()
                .subscribe (
                    {
                        emitter.onComplete()
                    },
                    {
                        repository.saveToDbHelpItemsFromFile()
                            .subscribe {
                                emitter.onComplete()
                            }
                    }
                )
        }
    }

}