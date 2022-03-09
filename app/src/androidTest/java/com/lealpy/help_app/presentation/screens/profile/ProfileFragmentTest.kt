package com.lealpy.help_app.presentation.screens.profile

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.lealpy.help_app.R
import com.lealpy.help_app.data.repository.TestFireRepositoryImpl.Companion.TEST_USER
import com.lealpy.help_app.di.RepositoryModule
import com.lealpy.help_app.di.UseCasesFirebaseModule
import com.lealpy.help_app.launchFragmentInHiltContainer
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
    UseCasesFirebaseModule::class
)
@HiltAndroidTest
class ProfileFragmentTest {

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
    fun show_UserUi_when_ProfileFragment_starts() {
        /** Given */

        /** When */
        launchFragmentInHiltContainer<ProfileFragment>()

        /** Then */
        Espresso.onView(ViewMatchers.withId(R.id.surnameAndName))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        "${TEST_USER.surname} ${TEST_USER.name}"
                    )
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.dateOfBirth))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        presentationMappers.getDateStringByTimestamp(
                            TEST_USER.dateOfBirth
                        )
                    )
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.fieldOfActivity))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        TEST_USER.fieldOfActivity
                    )
                )
            )
    }

}
