package com.lealpy.help_app.data.api

import com.lealpy.help_app.domain.model.HelpItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface HelpApi {
    @GET("./getHelpItems")
    @Headers("Content-Type: application/json")
    fun getHelpItems(): Single<List<HelpItem>>
}
