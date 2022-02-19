package com.lealpy.help_app.domain.use_cases.news

import com.lealpy.help_app.domain.repository.NewsRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class InsertToDbWatchedNewsIdUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(id : Long) : Completable {
        return repository.funInsertToDbWatchedNewsId(id)
    }

}
