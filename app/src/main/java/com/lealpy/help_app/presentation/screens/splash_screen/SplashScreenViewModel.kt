package com.lealpy.help_app.presentation.screens.splash_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.use_cases.firebase.GetAuthStateUseCase
import com.lealpy.help_app.domain.use_cases.news.GetUnwatchedNewsNumberUseCase
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getFromDbUnwatchedNewsNumberUseCase: GetUnwatchedNewsNumberUseCase,
    private val getAuthStateUseCase: GetAuthStateUseCase
) : ViewModel() {

    private val _startBadgeNumber = MutableLiveData<Int>()
    val startBadgeNumber: LiveData<Int> = _startBadgeNumber

    private val _navigateTo = MutableLiveData<Int>()
    val navigateTo: LiveData<Int> = _navigateTo

    private val disposable = CompositeDisposable()

    init {
        getNewsItemsFromServerOrFile()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun getNewsItemsFromServerOrFile() {
        disposable.add(
            getFromDbUnwatchedNewsNumberUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { unwatchedNewsNumber ->
                        _startBadgeNumber.value = unwatchedNewsNumber
                        getAuthState()
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                        getAuthState()
                    }
                )
        )
    }

    private fun getAuthState() {
        disposable.add(
            getAuthStateUseCase()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { authState ->
                        Thread.sleep(SLEEP_TIME_MILLIS)
                        if(authState) {
                            _navigateTo.postValue(R.id.actionSplashScreenFragmentToNavigationHelp)
                        } else {
                            _navigateTo.postValue(R.id.actionSplashScreenFragmentToAuthorizationFragment)
                        }
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                    }
                )
        )
    }

    companion object {
        private const val SLEEP_TIME_MILLIS = 500L
    }

}
