package com.lealpy.simbirsoft_training.presentation.news.news_description

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.data.api.NewsApi
import com.lealpy.simbirsoft_training.domain.model.NewsItem
import com.lealpy.simbirsoft_training.data.model.NewsItemJson
import com.lealpy.simbirsoft_training.utils.AppUtils
import com.lealpy.simbirsoft_training.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class NewsDescriptionViewModel @Inject constructor (
    private val newsApi: NewsApi,
    private val appUtils: AppUtils,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _newsItem = MutableLiveData <NewsItem> ()
    val newsItem : LiveData<NewsItem> = _newsItem

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _finishFragment = MutableLiveData <Boolean> ()
    val finishFragment : LiveData<Boolean> = _finishFragment

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun fetchNewsItem(newsItemId : Long) {
        _progressBarVisibility.value = View.VISIBLE

        compositeDisposable.add(newsApi
            .getNewsItemsJson()
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.schedulers.Schedulers.computation())
            .subscribe(
                { newsItemsJsonFromServer ->
                    item(newsItemsJsonFromServer, newsItemId)
                    _progressBarVisibility.postValue(View.GONE)
                },
                { error ->
                    error.message?.let { err -> Log.e(AppUtils.LOG_TAG, err) }
                    val newsItemsJsonFromFile = appUtils.getItemJsonFromFile<List<NewsItemJson>>(NEWS_ITEMS_JSON_FILE_NAME)
                    item(newsItemsJsonFromFile, newsItemId)
                    _progressBarVisibility.postValue(View.GONE)
                }
            )
        )
    }

    private fun item(newsItemsJson : List<NewsItemJson>, newsItemId : Long) {
        val newsItemJson = newsItemsJson.find { newsItemJson ->
            newsItemJson.id == newsItemId
        }
        if(newsItemJson != null) {
            val newsItem = appUtils.newsItemJsonToNewsItem(newsItemJson)
            _newsItem.postValue(newsItem)
        }
        else {
            _finishFragment.postValue(true)
        }
    }

    private fun showToast() {
        appUtils.showToast(resourceManager.getString(R.string.click_heard))
    }

    fun onSiteClicked() { showToast() }

    fun onDonateThingsClicked() { showToast() }

    fun onBecomeVolunteerClicked() { showToast() }

    fun onProfHelpClicked() { showToast() }

    fun onDonateMoneyClicked() { showToast() }

    fun onShareClicked() { showToast() }

    fun onSpanSiteClicked() { showToast() }

    fun onSpanFeedbackClicked() { showToast() }

    fun getId(newsItemId: Long) {
        fetchNewsItem(newsItemId)
    }

    companion object {
        private const val NEWS_ITEMS_JSON_FILE_NAME = "news_items.json"
    }

}
