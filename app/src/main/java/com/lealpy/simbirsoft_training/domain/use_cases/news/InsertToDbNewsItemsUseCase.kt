package com.lealpy.simbirsoft_training.domain.use_cases.news

import android.util.Log
import com.lealpy.simbirsoft_training.domain.repository.NewsRepository
import com.lealpy.simbirsoft_training.domain.utils.DomainUtils.Companion.LOG_TAG
import io.reactivex.Completable
import javax.inject.Inject

class InsertToDbNewsItemsUseCase @Inject constructor(
    private val repository : NewsRepository
) {

    fun execute() : Completable {
        return Completable.create { emitter ->
        repository.insertToDbNewsItemsFromServer()
            .subscribe(
                {
                    emitter.onComplete()
                },
                {
                    repository.getFromDbAllNewsItems()
                        .subscribe(
                            { newsItems ->
                                if(newsItems.isNullOrEmpty()) {
                                    repository.insertToDbNewsItemsFromFile()
                                        .subscribe{
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
