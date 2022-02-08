package com.lealpy.simbirsoft_training.domain.use_cases.news

import com.lealpy.simbirsoft_training.domain.repository.NewsRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertToDbWatchedNewsIdUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    fun execute(id : Long) : Completable {
        return repository.funInsertToDbWatchedNewsId(id)
    }

}