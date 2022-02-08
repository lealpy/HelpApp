package com.lealpy.simbirsoft_training.data.api

import com.lealpy.simbirsoft_training.domain.model.NkoItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NkoApi {
    @GET("./getNkoItems")
    @Headers("Content-Type: application/json")
    fun getNkoItems(): Single<List<NkoItem>>
}