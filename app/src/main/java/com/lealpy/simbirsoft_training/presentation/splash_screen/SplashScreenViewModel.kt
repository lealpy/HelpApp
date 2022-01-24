package com.lealpy.simbirsoft_training.presentation.splash_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.data.api.NewsApi
import com.lealpy.simbirsoft_training.data.model.NewsItemJson
import com.lealpy.simbirsoft_training.presentation.news.NewsViewModel.Companion.NEWS_ITEMS_JSON_FILE_NAME
import com.lealpy.simbirsoft_training.utils.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val newsApi: NewsApi,
    private val appUtils: AppUtils
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _startBadgeNumber = MutableLiveData<Int>()
    val startBadgeNumber: LiveData<Int> = _startBadgeNumber

    fun onActivityCreated() {
        compositeDisposable.add(newsApi
            .getNewsItemsJson()
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.schedulers.Schedulers.computation())
            .subscribe(
                { newsItemsJsonFromServer ->
                    val startBadgeNumber = newsItemsJsonFromServer.size
                    Thread.sleep(SCREEN_TIME_MILLIS)
                    _startBadgeNumber.postValue(startBadgeNumber)
                },
                { error ->
                    error.message?.let { err -> Log.e(AppUtils.LOG_TAG, err) }
                    val newsItemsJsonFromFile = appUtils.getItemJsonFromFile<List<NewsItemJson>>(NEWS_ITEMS_JSON_FILE_NAME)
                    val startBadgeNumber = newsItemsJsonFromFile.size
                    Thread.sleep(SCREEN_TIME_MILLIS)
                    _startBadgeNumber.postValue(startBadgeNumber)
                }
            )
        )
    }

    companion object {
        private const val SCREEN_TIME_MILLIS = 500L
    }

}
