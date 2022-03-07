package com.lealpy.help_app.data.api

import com.lealpy.help_app.domain.model.DonationHistoryItem
import com.lealpy.help_app.domain.model.NewsItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface NewsApi {
    @GET("./getNewsItems")
    @Headers("Content-Type: application/json")
    fun getNewsItems(): Single<List<NewsItem>>

    @POST("./donationHistoryItems")
    fun postDonationHistoryItem(@Body donationHistoryItem: DonationHistoryItem): Completable

    @GET("./donationHistoryItems")
    fun getDonationHistoryItems(): Single<List<DonationHistoryItem>>
}