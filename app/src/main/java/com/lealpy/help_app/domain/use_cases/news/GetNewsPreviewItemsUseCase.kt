package com.lealpy.help_app.domain.use_cases.news

import com.lealpy.help_app.domain.model.NewsPreviewItem
import com.lealpy.help_app.domain.repository.NewsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetNewsPreviewItemsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    operator fun invoke(): Single<List<NewsPreviewItem>> {
        return repository.getAllNewsPreviewItems()
    }

}
