package com.lealpy.help_app.domain.use_cases.news

import com.lealpy.help_app.domain.repository.NewsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUnwatchedNewsNumberUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke() : Single<Int> {
        return newsRepository.getUnwatchedNewsNumber()
    }

}
