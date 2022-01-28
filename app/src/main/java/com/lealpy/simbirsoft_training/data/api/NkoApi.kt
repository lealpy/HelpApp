package com.lealpy.simbirsoft_training.data.api

import com.lealpy.simbirsoft_training.ui.search.search_by_nko.NkoItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NkoApi {
    @GET("./getNkoItems")
    @Headers("Content-Type: application/json")
    fun getNkoItemsJson(): Single<List<NkoItem>>
}