package com.lealpy.simbirsoft_training.presentation.screens.splash_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.data.api.NewsApi
import com.lealpy.simbirsoft_training.domain.model.NewsItem
import com.lealpy.simbirsoft_training.presentation.screens.news.NewsViewModel.Companion.NEWS_ITEMS_JSON_FILE_NAME
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val newsApi: NewsApi,
    private val utils: PresentationUtils
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _startBadgeNumber = MutableLiveData<Int>()
    val startBadgeNumber: LiveData<Int> = _startBadgeNumber

    fun onViewCreated() {
        compositeDisposable.add(newsApi
            .getNewsItems()
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.schedulers.Schedulers.computation())
            .subscribe(
                { newsItemsJsonFromServer ->
                    val startBadgeNumber = newsItemsJsonFromServer.size
                    Thread.sleep(SCREEN_TIME_MILLIS)
                    _startBadgeNumber.postValue(startBadgeNumber)
                },
                { error ->
                    error.message?.let { err -> Log.e(PresentationUtils.LOG_TAG, err) }
                    val newsItemsJsonFromFile = utils.getItemsFromFile<List<NewsItem>>(NEWS_ITEMS_JSON_FILE_NAME)
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
