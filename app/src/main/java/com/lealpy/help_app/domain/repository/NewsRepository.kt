package com.lealpy.help_app.domain.repository

import com.lealpy.help_app.domain.model.DonationHistoryItem
import com.lealpy.help_app.domain.model.NewsDescriptionItem
import com.lealpy.help_app.domain.model.NewsPreviewItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface NewsRepository {
    fun getAllNewsPreviewItems(): Single<List<NewsPreviewItem>>
    fun getNewsDescriptionItem(id: Long): Single<NewsDescriptionItem>
    fun funInsertToDbWatchedNewsId(id: Long): Completable
    fun getUnwatchedNewsNumber(): Single<Int>
    fun addDonationHistoryItem(donationHistoryItem: DonationHistoryItem): Completable
    fun getDonationHistoryItems(): Single<List<DonationHistoryItem>>
}
