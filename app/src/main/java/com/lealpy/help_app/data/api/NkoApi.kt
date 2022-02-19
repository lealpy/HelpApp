package com.lealpy.help_app.data.api

import com.lealpy.help_app.domain.model.NkoItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NkoApi {
    @GET("./getNkoItems")
    @Headers("Content-Type: application/json")
    fun getNkoItems(): Single<List<NkoItem>>
}