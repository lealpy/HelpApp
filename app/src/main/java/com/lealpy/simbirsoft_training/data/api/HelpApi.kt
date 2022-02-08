package com.lealpy.simbirsoft_training.data.api

import com.lealpy.simbirsoft_training.domain.model.HelpItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface HelpApi {
    @GET("./getHelpItems")
    @Headers("Content-Type: application/json")
    fun getHelpItems(): Single<List<HelpItem>>
}
