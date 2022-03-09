package com.lealpy.help_app.domain.model

data class DonationHistoryItem(
    val id: String,
    val newsId: Long,
    val newsTitle: String,
    val date: Long,
    val donationAmount: Int,
)
