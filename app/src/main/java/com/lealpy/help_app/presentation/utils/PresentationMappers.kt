package com.lealpy.help_app.presentation.utils

import android.content.Context
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.model.*
import com.lealpy.help_app.presentation.model.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PresentationMappers @Inject constructor(
    val appContext: Context,
    private val utils: PresentationUtils,
) {

    private val calendar by lazy { GregorianCalendar() }

    fun helpItemsToHelpItemsUi(helpItems: List<HelpItem>): List<HelpItemUi> {
        return helpItems.map { helpItem ->
            val image = utils.getBitmapFromUrl(helpItem.imageUrl)

            HelpItemUi(
                id = helpItem.id,
                image = image,
                text = helpItem.text
            )
        }
    }

    fun newsPreviewItemsToNewsPreviewItemsUi(
        newsPreviewItems: List<NewsPreviewItem>,
    ): List<NewsPreviewItemUi> {
        return newsPreviewItems.map { newsPreviewItem ->
            val image = utils.getBitmapFromUrl(newsPreviewItem.imageUrl)
            NewsPreviewItemUi(
                id = newsPreviewItem.id,
                image = image,
                title = newsPreviewItem.title,
                abbreviatedText = newsPreviewItem.abbreviatedText,
                date = getDateStringByTimestamp(newsPreviewItem.date),
                isChildrenCategory = newsPreviewItem.isChildrenCategory,
                isAdultCategory = newsPreviewItem.isAdultCategory,
                isElderlyCategory = newsPreviewItem.isElderlyCategory,
                isAnimalCategory = newsPreviewItem.isAnimalCategory,
                isEventCategory = newsPreviewItem.isEventCategory
            )
        }
    }

    fun newsDescriptionItemToNewsDescriptionItemUi(
        newsDescriptionItem: NewsDescriptionItem,
    ): NewsDescriptionItemUi {
        val image = utils.getBitmapFromUrl(newsDescriptionItem.imageUrl)
        val image2 = utils.getBitmapFromUrl(newsDescriptionItem.image2Url)
        val image3 = utils.getBitmapFromUrl(newsDescriptionItem.image3Url)

        return NewsDescriptionItemUi(
            id = newsDescriptionItem.id,
            image = image,
            title = newsDescriptionItem.title,
            abbreviatedText = newsDescriptionItem.abbreviatedText,
            date = getDateStringByTimestamp(newsDescriptionItem.date),
            fundName = newsDescriptionItem.fundName,
            address = newsDescriptionItem.address,
            phone = newsDescriptionItem.phone,
            image2 = image2,
            image3 = image3,
            fullText = newsDescriptionItem.fullText,
        )
    }

    fun userToUserUi(user: User): UserUi {
        val avatar = utils.getBitmapFromUrl(user.avatarUrl)

        return UserUi(
            id = user.id,
            name = user.name,
            surname = user.surname,
            dateOfBirth = getDateStringByTimestamp(user.dateOfBirth),
            fieldOfActivity = user.fieldOfActivity,
            email = user.email,
            avatar = avatar
        )
    }

    fun donationHistoryItemsToDonationHistoryItemsUi(
        donationHistoryItems: List<DonationHistoryItem>,
    ): List<DonationHistoryItemUi> {
        return donationHistoryItems.map { donationHistoryItem ->
            DonationHistoryItemUi(
                id = donationHistoryItem.id,
                newsTitle = donationHistoryItem.newsTitle,
                date = getDateStringByTimestamp(donationHistoryItem.date),
                donationAmount = "${donationHistoryItem.donationAmount} ₽"
            )
        }
    }

    fun getDateStringByTimestamp(date: Long): String {
        val day = getDayIntCurrentTimezoneByTimestamp(date)
        val month = getMonthIntCurrentTimezoneByTimestamp(date)
        val year = getYearIntCurrentTimezoneByTimestamp(date)
        val months = appContext.resources.getStringArray(R.array.months)

        return when (month) {
            1 -> "$day ${months[0]} $year"
            2 -> "$day ${months[1]} $year"
            3 -> "$day ${months[2]} $year"
            4 -> "$day ${months[3]} $year"
            5 -> "$day ${months[4]} $year"
            6 -> "$day ${months[5]} $year"
            7 -> "$day ${months[6]} $year"
            8 -> "$day ${months[7]} $year"
            9 -> "$day ${months[8]} $year"
            10 -> "$day ${months[9]} $year"
            11 -> "$day ${months[10]} $year"
            12 -> "$day ${months[11]} $year"
            else -> ""
        }
    }

    fun getTimestampGmtByInt(
        year: Int, month: Int, day: Int, hour: Int = 0, minute: Int = 0, second: Int = 0,
    ): Long {
        return GregorianCalendar(year, month - 1, day, hour, minute, second)
            .timeInMillis + getGmtDelta()
    }

    fun getYearIntCurrentTimezoneByTimestamp(date: Long): Int {
        return SimpleDateFormat("yyyy", Locale.getDefault())
            .format(Date(date )).toInt()
    }

    fun getMonthIntCurrentTimezoneByTimestamp(date: Long): Int {
        return SimpleDateFormat("MM", Locale.getDefault())
            .format(Date(date)).toInt()
    }

    fun getDayIntCurrentTimezoneByTimestamp(date: Long): Int {
        return SimpleDateFormat("dd", Locale.getDefault())
            .format(Date(date)).toInt()
    }

    fun getCurrentTimeGmt(): Long {
        return System.currentTimeMillis() - getGmtDelta()
    }

    private fun getGmtDelta(): Long {
        return calendar.timeZone.rawOffset.toLong()
    }

}
