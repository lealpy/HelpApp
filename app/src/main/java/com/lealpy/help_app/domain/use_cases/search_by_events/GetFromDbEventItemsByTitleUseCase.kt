package com.lealpy.help_app.domain.use_cases.search_by_events

import com.lealpy.help_app.domain.model.EventItem
import com.lealpy.help_app.domain.repository.EventRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetFromDbEventItemsByTitleUseCase @Inject constructor(
    private val repository: EventRepository
){

    operator fun invoke(searchQuery : String) : Single<List<EventItem>> {
        return if(searchQuery.isBlank()) {
            Single.just(emptyList())
        } else {
            repository.getFromDbEventItemsByTitle(searchQuery)
        }
    }

}
