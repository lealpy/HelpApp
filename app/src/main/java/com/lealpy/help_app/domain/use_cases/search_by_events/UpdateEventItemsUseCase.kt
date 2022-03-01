package com.lealpy.help_app.domain.use_cases.search_by_events

import com.lealpy.help_app.domain.repository.EventRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UpdateEventItemsUseCase @Inject constructor(
    private val repository: EventRepository,
) {

    operator fun invoke(): Completable {
        return repository.updateEventItems()
    }

}
