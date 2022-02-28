package com.lealpy.help_app.presentation.screens.news.news_preview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lealpy.help_app.domain.model.NewsPreviewItem
import com.lealpy.help_app.domain.use_cases.news.GetNewsPreviewItemsUseCase
import com.lealpy.help_app.domain.use_cases.news.GetUnwatchedNewsNumberUseCase
import com.lealpy.help_app.domain.use_cases.news.InsertToDbWatchedNewsIdUseCase
import com.lealpy.help_app.presentation.model.NewsPreviewItemUi
import com.lealpy.help_app.presentation.screens.getOrAwaitValue
import com.lealpy.help_app.presentation.utils.PresentationMappers
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class NewsPreviewViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var newsPreviewViewModel: NewsPreviewViewModel

    private val presentationMappers = Mockito.mock(PresentationMappers::class.java)

    private val getNewsPreviewItemsUseCase = Mockito.mock(GetNewsPreviewItemsUseCase::class.java)

    private val getUnwatchedNewsNumberUseCase =
        Mockito.mock(GetUnwatchedNewsNumberUseCase::class.java)

    private val insertToDbWatchedNewsIdUseCase =
        Mockito.mock(InsertToDbWatchedNewsIdUseCase::class.java)

    private val initNewsItems = listOf(
        NewsPreviewItem(
            id = INIT_NEWS_ITEM_ID,
            imageUrl = NEWS_ITEM_IMAGE_URL,
            title = INIT_NEWS_ITEM_TITLE,
            abbreviatedText = INIT_NEWS_ITEM_ABBREVIATED_TEXT,
            date = INIT_NEWS_ITEM_DATE_LONG,
            isChildrenCategory = INIT_NEWS_ITEM_IS_CHILDREN_CATEGORY,
            isAdultCategory = INIT_NEWS_ITEM_IS_ADULT_CATEGORY,
            isElderlyCategory = INIT_NEWS_ITEM_IS_ELDERLY_CATEGORY,
            isAnimalCategory = INIT_NEWS_ITEM_IS_ANIMAL_CATEGORY,
            isEventCategory = INIT_NEWS_ITEM_IS_EVENT_CATEGORY
        )
    )

    private val initNewsItemsUi = listOf(
        NewsPreviewItemUi(
            id = INIT_NEWS_ITEM_ID,
            image = NEWS_ITEM_IMAGE,
            title = INIT_NEWS_ITEM_TITLE,
            abbreviatedText = INIT_NEWS_ITEM_ABBREVIATED_TEXT,
            date = INIT_NEWS_ITEM_DATE_STRING,
            isChildrenCategory = INIT_NEWS_ITEM_IS_CHILDREN_CATEGORY,
            isAdultCategory = INIT_NEWS_ITEM_IS_ADULT_CATEGORY,
            isElderlyCategory = INIT_NEWS_ITEM_IS_ELDERLY_CATEGORY,
            isAnimalCategory = INIT_NEWS_ITEM_IS_ANIMAL_CATEGORY,
            isEventCategory = INIT_NEWS_ITEM_IS_EVENT_CATEGORY
        )
    )

    private fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        Mockito.`when`(getNewsPreviewItemsUseCase())
            .thenReturn(Single.just(initNewsItems))
        Mockito.`when`(getUnwatchedNewsNumberUseCase())
            .thenReturn(Single.just(INIT_UNWATCHED_NEWS_NUMBER))
        Mockito.`when`(presentationMappers.newsPreviewItemsToNewsPreviewItemsUi(initNewsItems))
            .thenReturn(initNewsItemsUi)

        newsPreviewViewModel = NewsPreviewViewModel(
            mappers = presentationMappers,
            getNewsPreviewItemsUseCase = getNewsPreviewItemsUseCase,
            getUnwatchedNewsNumberUseCase = getUnwatchedNewsNumberUseCase,
            insertToDbWatchedNewsIdUseCase = insertToDbWatchedNewsIdUseCase
        )
    }

    @Test
    fun `get unwatched news number when NewsPreviewViewModel inits`() {
        /** Given */

        /** When */
        setup()

        /** Then */
        val expected = INIT_UNWATCHED_NEWS_NUMBER
        val actual = newsPreviewViewModel.badgeNumber.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get unwatched news number when refresh swiped`() {
        /** Given */
        setup()
        Mockito.`when`(getUnwatchedNewsNumberUseCase())
            .thenReturn(Single.just(RUNTIME_UNWATCHED_NEWS_NUMBER))

        /** When */
        newsPreviewViewModel.onSwipedRefresh()

        /** Then */
        val expected = RUNTIME_UNWATCHED_NEWS_NUMBER
        val actual = newsPreviewViewModel.badgeNumber.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get unwatched news number when news watched`() {
        /** Given */
        setup()
        Mockito.`when`(getUnwatchedNewsNumberUseCase())
            .thenReturn(Single.just(RUNTIME_UNWATCHED_NEWS_NUMBER))
        Mockito.`when`(insertToDbWatchedNewsIdUseCase(anyLong())).thenReturn(Completable.complete())

        /** When */
        newsPreviewViewModel.onNewsWatched(RUNTIME_NEWS_ITEM_ID)

        /** Then */
        val expected = RUNTIME_UNWATCHED_NEWS_NUMBER
        val actual = newsPreviewViewModel.badgeNumber.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get newsPreviewItems when NewsPreviewViewModel inits`() {
        /** Given */

        /** When */
        setup()

        /** Then */
        val expected = initNewsItemsUi
        val actual = newsPreviewViewModel.newsPreviewItemsUi.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `get newsPreviewItems when refresh swiped`() {
        /** Given */
        setup()
        val runtimeNewsItems = listOf(
            NewsPreviewItem(
                id = RUNTIME_NEWS_ITEM_ID,
                imageUrl = NEWS_ITEM_IMAGE_URL,
                title = RUNTIME_NEWS_ITEM_TITLE,
                abbreviatedText = RUNTIME_NEWS_ITEM_ABBREVIATED_TEXT,
                date = RUNTIME_NEWS_ITEM_DATE_LONG,
                isChildrenCategory = RUNTIME_NEWS_ITEM_IS_CHILDREN_CATEGORY,
                isAdultCategory = RUNTIME_NEWS_ITEM_IS_ADULT_CATEGORY,
                isElderlyCategory = RUNTIME_NEWS_ITEM_IS_ELDERLY_CATEGORY,
                isAnimalCategory = RUNTIME_NEWS_ITEM_IS_ANIMAL_CATEGORY,
                isEventCategory = RUNTIME_NEWS_ITEM_IS_EVENT_CATEGORY
            )
        )

        val runtimeNewsItemsUi = listOf(
            NewsPreviewItemUi(
                id = RUNTIME_NEWS_ITEM_ID,
                image = NEWS_ITEM_IMAGE,
                title = RUNTIME_NEWS_ITEM_TITLE,
                abbreviatedText = RUNTIME_NEWS_ITEM_ABBREVIATED_TEXT,
                date = RUNTIME_NEWS_ITEM_DATE_STRING,
                isChildrenCategory = RUNTIME_NEWS_ITEM_IS_CHILDREN_CATEGORY,
                isAdultCategory = RUNTIME_NEWS_ITEM_IS_ADULT_CATEGORY,
                isElderlyCategory = RUNTIME_NEWS_ITEM_IS_ELDERLY_CATEGORY,
                isAnimalCategory = RUNTIME_NEWS_ITEM_IS_ANIMAL_CATEGORY,
                isEventCategory = RUNTIME_NEWS_ITEM_IS_EVENT_CATEGORY
            )
        )

        val runtimeGetItemsUseCaseResult = Single.just(runtimeNewsItems)
        Mockito.`when`(getNewsPreviewItemsUseCase()).thenReturn(runtimeGetItemsUseCaseResult)
        Mockito.`when`(presentationMappers.newsPreviewItemsToNewsPreviewItemsUi(runtimeNewsItems))
            .thenReturn(runtimeNewsItemsUi)

        /** When */
        newsPreviewViewModel.onSwipedRefresh()

        /** Then */
        val expected = runtimeNewsItemsUi
        val actual = newsPreviewViewModel.newsPreviewItemsUi.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `update LiveData when filters applied`() {
        /** Given */
        setup()
        val isChildrenChecked = true
        val isAdultChecked = false
        val isElderlyChecked = true
        val isAnimalChecked = false
        val isEventChecked = true

        /** When */
        newsPreviewViewModel.applyFilters(
            isChildrenChecked = isChildrenChecked,
            isAdultChecked = isAdultChecked,
            isElderlyChecked = isElderlyChecked,
            isAnimalChecked = isAnimalChecked,
            isEventChecked = isEventChecked
        )

        /** Then */
        Assert.assertEquals(
            isChildrenChecked,
            newsPreviewViewModel.isChildrenChecked.getOrAwaitValue()
        )

        Assert.assertEquals(
            isAdultChecked,
            newsPreviewViewModel.isAdultChecked.getOrAwaitValue()
        )

        Assert.assertEquals(
            isElderlyChecked,
            newsPreviewViewModel.isElderlyChecked.getOrAwaitValue()
        )

        Assert.assertEquals(
            isAnimalChecked,
            newsPreviewViewModel.isAnimalChecked.getOrAwaitValue()
        )

        Assert.assertEquals(
            isEventChecked,
            newsPreviewViewModel.isEventChecked.getOrAwaitValue()
        )
    }

    companion object {
        private const val NEWS_ITEM_IMAGE_URL = ""
        private val NEWS_ITEM_IMAGE = null

        private const val INIT_UNWATCHED_NEWS_NUMBER = 10
        private const val INIT_NEWS_ITEM_ID = 0L
        private const val INIT_NEWS_ITEM_TITLE = "INIT_NEWS_ITEM_TITLE"
        private const val INIT_NEWS_ITEM_ABBREVIATED_TEXT = "INIT_NEWS_ITEM_ABBREVIATED_TEXT"
        private const val INIT_NEWS_ITEM_DATE_LONG = 1640995200000L
        private const val INIT_NEWS_ITEM_DATE_STRING = "01 января 2022"
        private const val INIT_NEWS_ITEM_IS_CHILDREN_CATEGORY = true
        private const val INIT_NEWS_ITEM_IS_ADULT_CATEGORY = false
        private const val INIT_NEWS_ITEM_IS_ELDERLY_CATEGORY = true
        private const val INIT_NEWS_ITEM_IS_ANIMAL_CATEGORY = false
        private const val INIT_NEWS_ITEM_IS_EVENT_CATEGORY = true

        private const val RUNTIME_UNWATCHED_NEWS_NUMBER = 7
        private const val RUNTIME_NEWS_ITEM_ID = 1L
        private const val RUNTIME_NEWS_ITEM_TITLE = "RUNTIME_NEWS_ITEM_TITLE"
        private const val RUNTIME_NEWS_ITEM_ABBREVIATED_TEXT = "RUNTIME_NEWS_ITEM_ABBREVIATED_TEXT"
        private const val RUNTIME_NEWS_ITEM_DATE_LONG = 1595030400L
        private const val RUNTIME_NEWS_ITEM_DATE_STRING = "18 июля 2020"
        private const val RUNTIME_NEWS_ITEM_IS_CHILDREN_CATEGORY = false
        private const val RUNTIME_NEWS_ITEM_IS_ADULT_CATEGORY = true
        private const val RUNTIME_NEWS_ITEM_IS_ELDERLY_CATEGORY = false
        private const val RUNTIME_NEWS_ITEM_IS_ANIMAL_CATEGORY = true
        private const val RUNTIME_NEWS_ITEM_IS_EVENT_CATEGORY = false
    }

}
