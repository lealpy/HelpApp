package com.lealpy.help_app.data.api

import com.lealpy.help_app.domain.model.EventItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface EventApi {
    @GET("./getEventItems")
    @Headers("Content-Type: application/json")
    fun getEventItems(): Single<List<EventItem>>
}
