package com.lealpy.help_app.presentation.screens.profile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lealpy.help_app.domain.model.User
import com.lealpy.help_app.domain.use_cases.firebase.GetUserUseCase
import com.lealpy.help_app.domain.use_cases.firebase.SaveAvatarToStorageUseCase
import com.lealpy.help_app.domain.use_cases.firebase.SignOutUseCase
import com.lealpy.help_app.domain.use_cases.prefs.GetSettingGetPushUseCase
import com.lealpy.help_app.domain.use_cases.prefs.SetSettingGetPushUseCase
import com.lealpy.help_app.presentation.model.UserUi
import com.lealpy.help_app.presentation.screens.getOrAwaitValue
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.lealpy.help_app.presentation.utils.PresentationUtils
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class ProfileViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var profileViewModel: ProfileViewModel

    private val presentationUtils = Mockito.mock(PresentationUtils::class.java)
    private val getSettingGetPushUseCase = Mockito.mock(GetSettingGetPushUseCase::class.java)
    private val setSettingGetPushUseCase = Mockito.mock(SetSettingGetPushUseCase::class.java)
    private val getUserUseCase = Mockito.mock(GetUserUseCase::class.java)
    private val saveAvatarToStorageUseCase = Mockito.mock(SaveAvatarToStorageUseCase::class.java)
    private val signOutUseCase = Mockito.mock(SignOutUseCase::class.java)
    private val presentationMappers = Mockito.mock(PresentationMappers::class.java)

    private fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        Mockito.`when`(getUserUseCase()).thenReturn(Single.just(TEST_USER))
        Mockito.`when`(getSettingGetPushUseCase()).thenReturn(INIT_PUSH_SETTINGS)
        Mockito.`when`(presentationMappers.userToUserUi(TEST_USER)).thenReturn(TEST_USER_UI)

        profileViewModel = ProfileViewModel(
            utils = presentationUtils,
            getSettingGetPushUseCase = getSettingGetPushUseCase,
            setSettingGetPushUseCase = setSettingGetPushUseCase,
            getUserUseCase = getUserUseCase,
            saveAvatarToStorageUseCase = saveAvatarToStorageUseCase,
            signOutUseCase = signOutUseCase,
            mappers = presentationMappers
        )
    }

    @Test
    fun `update User LiveData when ProfileViewModel inits`() {
        /** Given */

        /** When */
        setup()

        /** Then */
        val expected = TEST_USER_UI
        val actual = profileViewModel.userUi.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `update settings LiveData when ProfileViewModel inits`() {
        /** Given */

        /** When */
        setup()

        /** Then */
        val expected = INIT_PUSH_SETTINGS
        val actual = profileViewModel.settingGetPush.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `update settings LiveData when settings changed`() {
        /** Given */
        setup()
        Mockito.`when`(setSettingGetPushUseCase(RUNTIME_PUSH_SETTINGS)).then { }

        /** When */
        profileViewModel.onSettingGetPushChanged(RUNTIME_PUSH_SETTINGS)

        /** Then */
        val expected = RUNTIME_PUSH_SETTINGS
        val actual = profileViewModel.settingGetPush.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    companion object {
        private val TEST_AVATAR = null
        private const val TEST_ID = "testId"
        private const val TEST_NAME = "testName"
        private const val TEST_SURNAME = "testSurname"
        private const val TEST_DATE_OF_BIRTH_LONG = 0L
        private const val TEST_DATE_OF_BIRTH_STRING = "1 января 1970"
        private const val TEST_FIELD_OF_ACTIVITY = "testFieldOfActivity"
        private const val TEST_EMAIL = "testEmail"
        private const val TEST_AVATAR_URL = "testImageUrl"

        private const val INIT_PUSH_SETTINGS = true
        private const val RUNTIME_PUSH_SETTINGS = !INIT_PUSH_SETTINGS

        private val TEST_USER = User(
            id = TEST_ID,
            name = TEST_NAME,
            surname = TEST_SURNAME,
            dateOfBirth = TEST_DATE_OF_BIRTH_LONG,
            fieldOfActivity = TEST_FIELD_OF_ACTIVITY,
            email = TEST_EMAIL,
            avatarUrl = TEST_AVATAR_URL
        )

        private val TEST_USER_UI = UserUi(
            id = TEST_ID,
            name = TEST_NAME,
            surname = TEST_SURNAME,
            dateOfBirth = TEST_DATE_OF_BIRTH_STRING,
            fieldOfActivity = TEST_FIELD_OF_ACTIVITY,
            email = TEST_EMAIL,
            avatar = TEST_AVATAR
        )
    }

}