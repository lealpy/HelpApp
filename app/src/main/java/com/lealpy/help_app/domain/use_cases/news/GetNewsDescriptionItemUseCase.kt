package com.lealpy.help_app.domain.use_cases.news

import com.lealpy.help_app.domain.model.NewsDescriptionItem
import com.lealpy.help_app.domain.repository.NewsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetNewsDescriptionItemUseCase @Inject constructor(
    private val repository : NewsRepository
) {

    operator fun invoke(id : Long) : Single<NewsDescriptionItem> {
        return repository.getNewsDescriptionItem(id)
    }

}
