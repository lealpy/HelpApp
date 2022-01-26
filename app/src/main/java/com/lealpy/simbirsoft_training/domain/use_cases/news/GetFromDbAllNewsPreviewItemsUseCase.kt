package com.lealpy.simbirsoft_training.domain.use_cases.news

import com.lealpy.simbirsoft_training.domain.model.NewsPreviewItem
import com.lealpy.simbirsoft_training.domain.repository.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFromDbAllNewsPreviewItemsUseCase @Inject constructor(
    private val repository : NewsRepository
) {

    fun execute() : Single<List<NewsPreviewItem>> {
        return repository.getFromDbAllNewsPreviewItems()
    }

}
