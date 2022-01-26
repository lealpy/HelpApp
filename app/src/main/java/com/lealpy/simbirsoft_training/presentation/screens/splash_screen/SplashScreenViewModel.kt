package com.lealpy.simbirsoft_training.presentation.screens.splash_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.domain.use_cases.news.GetUnwatchedNewsNumberUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.news.SaveToDbNewsItemsUseCase
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import com.lealpy.simbirsoft_training.utils.PresentationUtils.Companion.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val saveToDbNewsItemsUseCase: SaveToDbNewsItemsUseCase,
    private val getFromDbUnwatchedNewsNumberUseCase: GetUnwatchedNewsNumberUseCase,
    private val utils: PresentationUtils
) : ViewModel() {

    private val _startBadgeNumber = MutableLiveData<Int>()
    val startBadgeNumber: LiveData<Int> = _startBadgeNumber

    init {
        getNewsItemsFromServerOrFile()
    }

    private fun getNewsItemsFromServerOrFile() {
        saveToDbNewsItemsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe {
                getFromDbUnwatchedNewsNumber()
            }
    }

    private fun getFromDbUnwatchedNewsNumber() {
        getFromDbUnwatchedNewsNumberUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                { unwatchedNewsNumber ->
                    Thread.sleep(SCREEN_TIME_MILLIS)
                    _startBadgeNumber.postValue(unwatchedNewsNumber)
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                }
            )
    }

    companion object {
        private const val SCREEN_TIME_MILLIS = 500L
    }

}
