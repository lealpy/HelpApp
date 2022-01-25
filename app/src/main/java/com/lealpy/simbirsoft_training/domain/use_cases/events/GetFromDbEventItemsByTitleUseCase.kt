package com.lealpy.simbirsoft_training.domain.use_cases.events

import com.lealpy.simbirsoft_training.domain.model.EventItem
import com.lealpy.simbirsoft_training.domain.repository.EventRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFromDbEventItemsByTitleUseCase @Inject constructor(
    private val repository: EventRepository
){

    fun execute(searchQuery : String) : Single<List<EventItem>> {
        return repository.getFromDbEventItemsByTitle(searchQuery)
    }

}
