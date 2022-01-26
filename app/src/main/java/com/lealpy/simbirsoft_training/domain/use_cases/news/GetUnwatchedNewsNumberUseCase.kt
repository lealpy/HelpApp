package com.lealpy.simbirsoft_training.domain.use_cases.news

import com.lealpy.simbirsoft_training.domain.repository.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUnwatchedNewsNumberUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    fun execute() : Single<Int> {
        return newsRepository.getFromDbUnwatchedNewsId()
    }

}
