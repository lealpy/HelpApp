package com.lealpy.help_app.domain.repository

import com.lealpy.help_app.domain.model.EventItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface EventRepository {
    fun updateEventItems() : Completable
    fun getFromDbEventItemsByTitle(searchQuery : String) : Single<List<EventItem>>
}
