package com.lealpy.help_app.domain.use_cases.search_by_nko

import com.lealpy.help_app.domain.model.NkoItem
import com.lealpy.help_app.domain.repository.NkoRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFromDbNkoItemsByTitleUseCase @Inject constructor(
    private val repository: NkoRepository,
) {

    operator fun invoke(searchQuery: String): Single<List<NkoItem>> {
        return repository.getFromDbNkoItemsByTitle(searchQuery).map { nkoItems ->
            nkoItems.shuffled()
        }
    }

}
