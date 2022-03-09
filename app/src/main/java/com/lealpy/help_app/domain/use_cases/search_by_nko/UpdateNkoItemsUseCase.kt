package com.lealpy.help_app.domain.use_cases.search_by_nko

import com.lealpy.help_app.domain.repository.NkoRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UpdateNkoItemsUseCase @Inject constructor(
    private val repository: NkoRepository,
) {

    operator fun invoke(): Completable {
        return repository.updateNkoItems()
    }

}
