package com.lealpy.help_app.domain.use_cases.news

import com.lealpy.help_app.domain.model.DonationHistoryItem
import com.lealpy.help_app.domain.repository.NewsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetDonationHistoryItemsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(): Single<List<DonationHistoryItem>> {
        return repository.getDonationHistoryItems()
    }

}
