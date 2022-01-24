package com.lealpy.simbirsoft_training.presentation.news

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.data.api.NewsApi
import com.lealpy.simbirsoft_training.data.model.NewsItemJson
import com.lealpy.simbirsoft_training.domain.model.NewsItem
import com.lealpy.simbirsoft_training.utils.AppUtils
import com.lealpy.simbirsoft_training.utils.AppUtils.Companion.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsApi: NewsApi,
    private val appUtils: AppUtils
) : ViewModel() {

    private val _newsItems = MutableLiveData<List<NewsItem>>()
    val newsItems: LiveData<List<NewsItem>> = _newsItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _isChildrenChecked = MutableLiveData(true)
    val isChildrenChecked: LiveData<Boolean> = _isChildrenChecked

    private val _isAdultsChecked = MutableLiveData(true)
    val isAdultsChecked: LiveData<Boolean> = _isAdultsChecked

    private val _isElderlyChecked = MutableLiveData(true)
    val isElderlyChecked: LiveData<Boolean> = _isElderlyChecked

    private val _isAnimalsChecked = MutableLiveData(true)
    val isAnimalsChecked: LiveData<Boolean> = _isAnimalsChecked

    private val _isEventsChecked = MutableLiveData(true)
    val isEventsChecked: LiveData<Boolean> = _isEventsChecked

    private val _badgeNumber = MutableLiveData<Int>()
    val badgeNumber: LiveData<Int> = _badgeNumber

    private var loadedNewsItems = listOf<NewsItem>()

    private val viewedNewsId = mutableSetOf<Long>()

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun fetchNewsItems() {
        _progressBarVisibility.value = View.VISIBLE
        _isChildrenChecked.value = true
        _isAdultsChecked.value = true
        _isElderlyChecked.value = true
        _isAnimalsChecked.value = true
        _isEventsChecked.value = true

        compositeDisposable.add(newsApi
            .getNewsItemsJson()
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.schedulers.Schedulers.computation())
            .subscribe(
                { newsItemsJsonFromServer ->
                    val newsItemsResult = appUtils.newsItemsJsonToNewsItems(newsItemsJsonFromServer)
                    loadedNewsItems = newsItemsResult
                    _newsItems.postValue(newsItemsResult)
                    _progressBarVisibility.postValue(View.GONE)
                },
                { error ->
                    error.message?.let { err -> Log.e(LOG_TAG, err) }

                    val newsItemsJsonFromFile = appUtils.getItemJsonFromFile<List<NewsItemJson>>(NEWS_ITEMS_JSON_FILE_NAME)
                    val newsItemsResult = appUtils.newsItemsJsonToNewsItems(newsItemsJsonFromFile)
                    loadedNewsItems = newsItemsResult
                    _newsItems.postValue(newsItemsResult)
                    _progressBarVisibility.postValue(View.GONE)
                }
            )
        )
    }

    private fun updateNewsBadge() {
        var badgeNumber = 0
        _newsItems.value?.forEach { newsItem ->
            if(!viewedNewsId.contains(newsItem.id)) badgeNumber ++
        }
        _badgeNumber.postValue(badgeNumber)
    }

    fun applyFilters(
        isChildrenChecked: Boolean,
        isAdultsChecked: Boolean,
        isElderlyChecked: Boolean,
        isAnimalsChecked: Boolean,
        isEventsChecked: Boolean,
    ) {
        val filteredNewsSet = mutableSetOf<NewsItem>()

        loadedNewsItems.forEach { newsItem ->
            if(
                isChildrenChecked && newsItem.isChildrenCategory ||
                isAdultsChecked && newsItem.isAdultsCategory ||
                isElderlyChecked && newsItem.isElderlyCategory ||
                isAnimalsChecked && newsItem.isAnimalsCategory ||
                isEventsChecked && newsItem.isEventsCategory
            ) {
                filteredNewsSet.add(newsItem)
            }
        }

        _newsItems.value = filteredNewsSet.toList()
        _isChildrenChecked.value = isChildrenChecked
        _isAdultsChecked.value = isAdultsChecked
        _isElderlyChecked.value = isElderlyChecked
        _isAnimalsChecked.value = isAnimalsChecked
        _isEventsChecked.value = isEventsChecked
    }

    fun onNewsViewed(id: Long) {
        viewedNewsId.add(id)
        updateNewsBadge()
    }

    fun onNewsItemsUpdated() {
        updateNewsBadge()
    }

    fun onViewCreated() {
        if(_newsItems.value == null) fetchNewsItems()
    }

    fun onSwipedRefresh() {
        fetchNewsItems()
    }

    companion object {
        const val NEWS_ITEMS_JSON_FILE_NAME = "news_items.json"
    }

}
