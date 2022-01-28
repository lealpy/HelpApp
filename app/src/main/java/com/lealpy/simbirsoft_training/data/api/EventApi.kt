package com.lealpy.simbirsoft_training.data.api

import com.lealpy.simbirsoft_training.ui.search.search_by_events.EventItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface EventApi {
    @GET("./getEventItems")
    @Headers("Content-Type: application/json")
    fun getEventItemsJson(): Single<List<EventItem>>
}
