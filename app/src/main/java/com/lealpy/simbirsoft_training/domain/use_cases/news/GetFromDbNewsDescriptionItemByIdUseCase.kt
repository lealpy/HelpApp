package com.lealpy.simbirsoft_training.domain.use_cases.news

import com.lealpy.simbirsoft_training.domain.model.NewsDescriptionItem
import com.lealpy.simbirsoft_training.domain.repository.NewsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFromDbNewsDescriptionItemByIdUseCase @Inject constructor(
    private val repository : NewsRepository
) {

    fun execute(id : Long) : Single<NewsDescriptionItem> {
        return repository.getFromDbNewsDescriptionItem(id)
    }

}
