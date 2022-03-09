package com.lealpy.help_app.presentation.screens.news.news_preview

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.lealpy.help_app.R
import com.lealpy.help_app.data.repository.TestNewsRepositoryImpl.Companion.TEST_NEWS_PREVIEW_ITEMS
import com.lealpy.help_app.di.RepositoryModule
import com.lealpy.help_app.di.UseCasesNewsModule
import com.lealpy.help_app.launchFragmentInHiltContainer
import com.lealpy.help_app.presentation.screens.news.news_preview.NewsPreviewItemAdapter.NewsItemHolder
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.squareup.rx3.idler.Rx3Idler
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import it.xabaras.android.espresso.recyclerviewchildactions.RecyclerViewChildActions.Companion.childOfViewAtPositionWithMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(
    RepositoryModule::class,
    UseCasesNewsModule::class
)
@HiltAndroidTest
class NewsPreviewFragmentTest {

    @Inject
    lateinit var presentationMappers: PresentationMappers

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()

        RxJavaPlugins.setInitIoSchedulerHandler(
            Rx3Idler.create("RxJava 3.x IO Scheduler")
        )
        RxJavaPlugins.setInitComputationSchedulerHandler(
            Rx3Idler.create("RxJava 3.x Computation Scheduler")
        )
    }

    @Test
    fun show_recycler_view_when_NewsPreviewFragment_starts() {
        /** Given */

        /** When */
        launchFragmentInHiltContainer<NewsPreviewFragment>()

        /** Then */
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun check_content_of_recycler_view_items() {
        /** Given */

        /** When */
        launchFragmentInHiltContainer<NewsPreviewFragment>()
        Thread.sleep(5000)

        /** Then */
        for (position in 0..TEST_NEWS_PREVIEW_ITEMS.lastIndex) {
            onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.scrollToPosition<NewsItemHolder>(position)
            )

            onView(withId(R.id.recyclerView))
                .check(
                    matches(
                        childOfViewAtPositionWithMatcher(
                            R.id.newsItemTitle,
                            position,
                            withText(TEST_NEWS_PREVIEW_ITEMS[position].title)
                        )
                    )
                )
                .check(
                    matches(
                        childOfViewAtPositionWithMatcher(
                            R.id.newsItemText,
                            position,
                            withText(TEST_NEWS_PREVIEW_ITEMS[position].abbreviatedText)
                        )
                    )
                )
                .check(
                    matches(
                        childOfViewAtPositionWithMatcher(
                            R.id.newsItemDate,
                            position,
                            withText(
                                presentationMappers.getDateStringByTimestamp(
                                    TEST_NEWS_PREVIEW_ITEMS[position].date
                                )
                            )
                        )
                    )
                )
        }
    }

}
