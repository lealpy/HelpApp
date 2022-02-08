package com.lealpy.simbirsoft_training.data.api

import com.lealpy.simbirsoft_training.domain.model.NewsItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {
    @GET("./getNewsItems")
    @Headers("Content-Type: application/json")
    fun getNewsItems(): Single<List<NewsItem>>
}