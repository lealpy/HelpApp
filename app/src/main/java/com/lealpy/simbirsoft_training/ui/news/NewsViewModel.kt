package com.lealpy.simbirsoft_training.ui.news

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.utils.AppUtils
import java.util.concurrent.Executors

class NewsViewModel(application: Application) : AndroidViewModel(application) {

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

    private val requestManager = Glide.with(getApplication<Application>())

    fun getNewsItems() {

        _isChildrenChecked.value = true
        _isAdultsChecked.value = true
        _isElderlyChecked.value = true
        _isAnimalsChecked.value = true
        _isEventsChecked.value = true

        val executorService = Executors.newSingleThreadExecutor()

        executorService.execute {
            _progressBarVisibility.postValue(View.VISIBLE)
            Thread.sleep(THREAD_SLEEP_MILLIS)
            val jsonFileString = AppUtils.getJsonDataFromAsset(getApplication(), NEWS_ITEMS_JSON_FILE_NAME)
            val gson = Gson()
            val itemTypes = object : TypeToken<List<NewsItemJSON>>() {}.type
            val newsItemsFromJson : List<NewsItemJSON> = gson.fromJson(jsonFileString, itemTypes)
            loadedNewsItems = AppUtils.newsItemsJsonToNewsItems(newsItemsFromJson, requestManager)
            _newsItems.postValue(loadedNewsItems)
            _progressBarVisibility.postValue(View.GONE)
        }

        executorService.shutdown()

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

    companion object {
        const val NEWS_ITEMS_JSON_FILE_NAME = "news_items.json"
        private const val THREAD_SLEEP_MILLIS : Long = 2000
    }

}
