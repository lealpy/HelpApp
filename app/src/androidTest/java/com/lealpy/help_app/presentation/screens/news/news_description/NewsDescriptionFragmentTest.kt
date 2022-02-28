package com.lealpy.help_app.presentation.screens.news.news_description

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.lealpy.help_app.R
import com.lealpy.help_app.data.repository.TestNewsRepositoryImpl.Companion.TEST_NEWS_DESCRIPTION_ITEM
import com.lealpy.help_app.di.RepositoryModule
import com.lealpy.help_app.di.UseCasesNewsModule
import com.lealpy.help_app.launchFragmentInHiltContainer
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_ID_KEY
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.squareup.rx3.idler.Rx3Idler
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(
    RepositoryModule::class,
    UseCasesNewsModule::class
)
@HiltAndroidTest
class NewsDescriptionFragmentTest {

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
    }

    @Test
    fun show_newsDescriptionItemUi_when_NewsDescriptionFragment_starts() {
        /** Given */
        val fragmentArgs = bundleOf(NEWS_FEATURE_NEWS_ID_KEY to TEST_NEWS_DESCRIPTION_ITEM.id)

        /** When */
        launchFragmentInHiltContainer<NewsDescriptionFragment>(fragmentArgs = fragmentArgs)

        /** Then */
        onView(withId(R.id.title)).check(matches(withText(TEST_NEWS_DESCRIPTION_ITEM.title)))
        onView(withId(R.id.date)).check(matches(withText(
            presentationMappers.getDateStringByTimestamp(TEST_NEWS_DESCRIPTION_ITEM.date)
        )))
        onView(withId(R.id.fundName)).check(matches(withText(TEST_NEWS_DESCRIPTION_ITEM.fundName)))
        onView(withId(R.id.address)).check(matches(withText(TEST_NEWS_DESCRIPTION_ITEM.address)))
        onView(withId(R.id.phone)).check(matches(withText(TEST_NEWS_DESCRIPTION_ITEM.phone)))
        onView(withId(R.id.fullText)).check(matches(withText(TEST_NEWS_DESCRIPTION_ITEM.fullText)))
    }

}
