package com.lealpy.help_app.data.api

import com.lealpy.help_app.domain.model.NewsItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {
    @GET("./getNewsItems")
    @Headers("Content-Type: application/json")
    fun getNewsItems(): Single<List<NewsItem>>
}