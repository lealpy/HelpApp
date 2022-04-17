package com.lealpy.help_app.presentation.utils

import android.content.Context
import android.content.res.Resources
import com.lealpy.help_app.domain.model.HelpItem
import com.lealpy.help_app.domain.model.NewsDescriptionItem
import com.lealpy.help_app.domain.model.NewsPreviewItem
import com.lealpy.help_app.domain.model.User
import com.lealpy.help_app.presentation.model.HelpItemUi
import com.lealpy.help_app.presentation.model.NewsDescriptionItemUi
import com.lealpy.help_app.presentation.model.NewsPreviewItemUi
import com.lealpy.help_app.presentation.model.UserUi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PresentationMappersTest {

    private val presentationUtils = Mockito.mock(PresentationUtils::class.java)

    private val mockContext = Mockito.mock(Context::class.java)

    private val mockResources = Mockito.mock(Resources::class.java)

    private val presentationMappers = PresentationMappers(
        mockContext,
        presentationUtils
    )

    @Test
    fun `get helpItemsUi from helpItems`() {
        /** Given */
        val testImage = null
        val testImageUrl = "testImageUrl"
        val testId = 0L
        val testText = "testText"
        val testHelpItems = listOf(HelpItem(id = testId, imageUrl = testImageUrl, text = testText))
        Mockito.`when`(presentationUtils.getBitmapFromUrl(anyString())).thenReturn(testImage)

        /** When */
        val testHelpItemsUi = presentationMappers.helpItemsToHelpItemsUi(testHelpItems)

        /** Then */
        val expected = listOf(HelpItemUi(id = testId, image = testImage, text = testText))
        val actual = testHelpItemsUi
        assertEquals(expected, actual)
    }

    @Test
    fun `get newsPreviewItemsUi from newsPreviewItems`() {
        /** Given */
        val testImage = null
        val testImageUrl = "testImageUrl"
        val testId = 192L
        val testTitle = "testText"
        val testAbbreviatedText = "testAbbreviatedText"
        val testDateLong = TEST_TIMESTAMP
        val testDateString = TEST_DATE_STRING
        val testIsChildrenCategory = true
        val testIsAdultCategory = false
        val testIsElderlyCategory = true
        val testIsAnimalCategory = false
        val testIsEventCategory = true

        val testNewsPreviewItems = listOf(
            NewsPreviewItem(
                id = testId,
                imageUrl = testImageUrl,
                title = testTitle,
                abbreviatedText = testAbbreviatedText,
                date = testDateLong,
                isChildrenCategory = testIsChildrenCategory,
                isAdultCategory = testIsAdultCategory,
                isElderlyCategory = testIsElderlyCategory,
                isAnimalCategory = testIsAnimalCategory,
                isEventCategory = testIsEventCategory,
            )
        )

        val testMonths = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
            "августа", "сентября", "октября", "ноября", "декабря")

        Mockito.`when`(mockContext.resources).thenReturn(mockResources)
        Mockito.`when`(mockResources.getStringArray(anyInt())).thenReturn(testMonths)
        Mockito.`when`(presentationUtils.getBitmapFromUrl(anyString())).thenReturn(testImage)

        /** When */
        val testNewsPreviewItemsUi =
            presentationMappers.newsPreviewItemsToNewsPreviewItemsUi(testNewsPreviewItems)

        /** Then */
        val expected = listOf(
            NewsPreviewItemUi(
                id = testId,
                image = testImage,
                title = testTitle,
                abbreviatedText = testAbbreviatedText,
                date = testDateString,
                isChildrenCategory = testIsChildrenCategory,
                isAdultCategory = testIsAdultCategory,
                isElderlyCategory = testIsElderlyCategory,
                isAnimalCategory = testIsAnimalCategory,
                isEventCategory = testIsEventCategory,
            )
        )
        val actual = testNewsPreviewItemsUi
        assertEquals(expected, actual)
    }

    @Test
    fun `get newsDescriptionItemUi from newsDescriptionItem`() {
        /** Given */
        val testImage = null
        val testImageUrl = "testImageUrl"
        val testId = 192L
        val testTitle = "testText"
        val testAbbreviatedText = "testAbbreviatedText"
        val testDateLong = TEST_TIMESTAMP
        val testDateString = TEST_DATE_STRING
        val testFundName = "testFundName"
        val testAddress = "testAddress"
        val testPhone = "testPhone"
        val testFullText = "testFullText"

        val testNewsDescriptionItem = NewsDescriptionItem(
            id = testId,
            imageUrl = testImageUrl,
            title = testTitle,
            abbreviatedText = testAbbreviatedText,
            date = testDateLong,
            fundName = testFundName,
            address = testAddress,
            phone = testPhone,
            image2Url = testImageUrl,
            image3Url = testImageUrl,
            fullText = testFullText,
        )

        val testMonths = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
            "августа", "сентября", "октября", "ноября", "декабря")

        Mockito.`when`(mockContext.resources).thenReturn(mockResources)
        Mockito.`when`(mockResources.getStringArray(anyInt())).thenReturn(testMonths)
        Mockito.`when`(presentationUtils.getBitmapFromUrl(anyString())).thenReturn(testImage)

        /** When */
        val testNewsDescriptionItemUi =
            presentationMappers.newsDescriptionItemToNewsDescriptionItemUi(testNewsDescriptionItem)

        /** Then */
        val expected =
            NewsDescriptionItemUi(
                id = testId,
                image = testImage,
                title = testTitle,
                abbreviatedText = testAbbreviatedText,
                date = testDateString,
                fundName = testFundName,
                address = testAddress,
                phone = testPhone,
                image2 = testImage,
                image3 = testImage,
                fullText = testFullText,
            )
        val actual = testNewsDescriptionItemUi
        assertEquals(expected, actual)
    }

    @Test
    fun `get userUi from user`() {
        /** Given */
        val testAvatar = null
        val testId = "testId"
        val testName = "testName"
        val testSurname = "testSurname"
        val testDateOfBirthLong = TEST_TIMESTAMP
        val testDateOfBirthString = TEST_DATE_STRING
        val testFieldOfActivity = "testFieldOfActivity"
        val testEmail = "testEmail"
        val testAvatarUrl = "testImageUrl"

        val testUser = User(
            id = testId,
            name = testName,
            surname = testSurname,
            dateOfBirth = testDateOfBirthLong,
            fieldOfActivity = testFieldOfActivity,
            email = testEmail,
            avatarUrl = testAvatarUrl
        )

        val testMonths = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
            "августа", "сентября", "октября", "ноября", "декабря")

        Mockito.`when`(mockContext.resources).thenReturn(mockResources)
        Mockito.`when`(mockResources.getStringArray(anyInt())).thenReturn(testMonths)
        Mockito.`when`(presentationUtils.getBitmapFromUrl(anyString())).thenReturn(testAvatar)

        /** When */
        val testUserUi = presentationMappers.userToUserUi(testUser)

        /** Then */
        val expected = UserUi(
            id = testId,
            name = testName,
            surname = testSurname,
            dateOfBirth = testDateOfBirthString,
            fieldOfActivity = testFieldOfActivity,
            email = testEmail,
            avatar = testAvatar
        )
        val actual = testUserUi
        assertEquals(expected, actual)
    }

    @Test
    fun `get Timestamp by Int`() {
        /** Given */
        val testYear = TEST_YEAR
        val testMonth = TEST_MONTH
        val testDay = TEST_DAY
        val testHour = TEST_HOUR
        val testMinute = TEST_MINUTE
        val testSecond = TEST_SECOND

        /** When */
        val testTimestamp = presentationMappers.getTimestampGmtByInt(
            year = testYear,
            month = testMonth,
            day = testDay,
            hour = testHour,
            minute = testMinute,
            second = testSecond
        )

        /** Then */
        val expected = TEST_TIMESTAMP
        val actual = testTimestamp
        assertEquals(expected, actual)
    }

    @Test
    fun `get year by Timestamp`() {
        /** Given */
        val testTimestamp = TEST_TIMESTAMP

        /** When */
        val testYear = presentationMappers.getYearIntCurrentTimezoneByTimestamp(testTimestamp)

        /** Then */
        val expected = TEST_YEAR
        val actual = testYear
        assertEquals(expected, actual)
    }

    @Test
    fun `get month by Timestamp`() {
        /** Given */
        val testTimestamp = TEST_TIMESTAMP

        /** When */
        val testMonth = presentationMappers.getMonthIntCurrentTimezoneByTimestamp(testTimestamp)

        /** Then */
        val expected = TEST_MONTH
        val actual = testMonth
        assertEquals(expected, actual)
    }

    @Test
    fun `get day by Timestamp`() {
        /** Given */
        val testTimestamp = TEST_TIMESTAMP

        /** When */
        val testDay = presentationMappers.getDayIntCurrentTimezoneByTimestamp(testTimestamp)

        /** Then */
        val expected = TEST_DAY
        val actual = testDay
        assertEquals(expected, actual)
    }

    @Test
    fun `get date String by Timestamp`() {
        /** Given */
        val testTimestamp = TEST_TIMESTAMP
        val testMonths = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
            "августа", "сентября", "октября", "ноября", "декабря")

        Mockito.`when`(mockContext.resources).thenReturn(mockResources)
        Mockito.`when`(mockResources.getStringArray(anyInt())).thenReturn(testMonths)

        /** When */
        val testDateString = presentationMappers.getDateStringByTimestamp(testTimestamp)

        /** Then */
        val expected = TEST_DATE_STRING
        val actual = testDateString
        assertEquals(expected, actual)
    }

    companion object {
        private const val TEST_TIMESTAMP = 1595115915000L
        private const val TEST_YEAR = 2020
        private const val TEST_MONTH = 7
        private const val TEST_DAY = 18
        private const val TEST_HOUR = 23
        private const val TEST_MINUTE = 45
        private const val TEST_SECOND = 15
        private const val TEST_DATE_STRING = "19 июля 2020" // Для GMT -> 18 июля; GMT+1 -> 19 июля
    }

}
