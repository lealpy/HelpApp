package com.lealpy.simbirsoft_training.domain.repository

import com.lealpy.simbirsoft_training.domain.model.EventItem
import io.reactivex.Completable
import io.reactivex.Single

interface EventRepository {
    fun insertToDbEventItemsFromServer() : Completable
    fun insertToDbEventItemsFromFile() : Completable
    fun getFromDbEventItemsByTitle(searchQuery : String) : Single<List<EventItem>>
    fun getFromDbAllEventItems() : Single<List<EventItem>>
    fun insertToDbEventItems(eventItems : List<EventItem>) : Completable
}
