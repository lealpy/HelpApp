package com.lealpy.help_app.presentation.screens.help

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lealpy.help_app.domain.model.HelpItem
import com.lealpy.help_app.domain.use_cases.help.GetHelpItemsUseCase
import com.lealpy.help_app.presentation.model.HelpItemUi
import com.lealpy.help_app.presentation.screens.getOrAwaitValue
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.lealpy.help_app.presentation.utils.PresentationUtils
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class HelpViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var helpViewModel: HelpViewModel

    private val getHelpItemsUseCase = Mockito.mock(GetHelpItemsUseCase::class.java)
    private val presentationUtils = Mockito.mock(PresentationUtils::class.java)
    private val presentationMappers = Mockito.mock(PresentationMappers::class.java)

    private val initHelpItems =
        listOf(HelpItem(INIT_HELP_ITEM_ID, HELP_ITEM_IMAGE_URL, INIT_HELP_ITEM_TEXT))
    private val initHelpItemsUi =
        listOf(HelpItemUi(INIT_HELP_ITEM_ID, HELP_ITEM_IMAGE, INIT_HELP_ITEM_TEXT))

    private fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        val initGetItemsUseCaseResult = Single.just(initHelpItems)
        Mockito.`when`(getHelpItemsUseCase()).thenReturn(initGetItemsUseCaseResult)
        Mockito.`when`(presentationMappers.helpItemsToHelpItemsUi(initHelpItems))
            .thenReturn(initHelpItemsUi)

        helpViewModel = HelpViewModel(
            getHelpItemsUseCase = getHelpItemsUseCase,
            utils = presentationUtils,
            mappers = presentationMappers,
        )
    }

    @Test
    fun `get helpItems when HelpViewModel inits`() {
        /** Given */

        /** When */
        setup()

        /** Then */
        val expected = initHelpItemsUi
        val actual = helpViewModel.helpItemsUi.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun `get helpItems when refresh swiped`() {
        /** Given */
        setup()
        val runtimeHelpItems =
            listOf(HelpItem(RUNTIME_HELP_ITEM_ID, HELP_ITEM_IMAGE_URL, RUNTIME_HELP_ITEM_TEXT))
        val runtimeHelpItemsUi =
            listOf(HelpItemUi(RUNTIME_HELP_ITEM_ID, HELP_ITEM_IMAGE, RUNTIME_HELP_ITEM_TEXT))
        val runtimeGetItemsUseCaseResult = Single.just(runtimeHelpItems)
        Mockito.`when`(getHelpItemsUseCase()).thenReturn(runtimeGetItemsUseCaseResult)
        Mockito.`when`(presentationMappers.helpItemsToHelpItemsUi(runtimeHelpItems))
            .thenReturn(runtimeHelpItemsUi)

        /** When */
        helpViewModel.onSwipedRefresh()

        /** Then */
        val expected = listOf(HelpItemUi(RUNTIME_HELP_ITEM_ID, null, RUNTIME_HELP_ITEM_TEXT))
        val actual = helpViewModel.helpItemsUi.getOrAwaitValue()
        assertEquals(expected, actual)
    }

    companion object {
        private const val HELP_ITEM_IMAGE_URL = ""
        private val HELP_ITEM_IMAGE = null

        private const val INIT_HELP_ITEM_ID = 0L
        private const val INIT_HELP_ITEM_TEXT = "INIT_HELP_ITEM_TEXT"

        private const val RUNTIME_HELP_ITEM_ID = 1L
        private const val RUNTIME_HELP_ITEM_TEXT = "RUNTIME_HELP_ITEM_TEXT"
    }

}
