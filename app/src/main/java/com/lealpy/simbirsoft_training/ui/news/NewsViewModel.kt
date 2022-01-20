package com.lealpy.simbirsoft_training.ui.news

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val requestManager = Glide.with(getApplication<Application>())

    fun getNewsItemsFromJSON() {

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
            val newsItemsResult = AppUtils.newsItemsJsonToNewsItems(newsItemsFromJson, requestManager)
            _newsItems.postValue(newsItemsResult)
            _progressBarVisibility.postValue(View.GONE)
        }

        executorService.shutdown()

    }

    fun applyFilters(
        isChildrenChecked: Boolean,
        isAdultsChecked: Boolean,
        isElderlyChecked: Boolean,
        isAnimalsChecked: Boolean,
        isEventsChecked: Boolean,
    ) {
        val childrenCategoryList = if(isChildrenChecked) {
            _newsItems.value?.filter { it.isChildrenCategory }
        } else emptyList()

        val adultCategoryList = if(isAdultsChecked) {
            _newsItems.value?.filter { it.isAdultsCategory }
        } else emptyList()

        val elderlyCategoryList = if(isElderlyChecked) {
            _newsItems.value?.filter { it.isElderlyCategory }
        } else emptyList()

        val animalsCategoryList = if(isAnimalsChecked) {
            _newsItems.value?.filter { it.isAnimalsCategory }
        } else emptyList()

        val eventsCategoryList = if(isEventsChecked) {
            _newsItems.value?.filter { it.isEventsCategory }
        } else emptyList()

        val filteredNewsSet = mutableSetOf<NewsItem>()

        if (childrenCategoryList != null) filteredNewsSet.addAll(childrenCategoryList)
        if (adultCategoryList != null) filteredNewsSet.addAll(adultCategoryList)
        if (elderlyCategoryList != null) filteredNewsSet.addAll(elderlyCategoryList)
        if (animalsCategoryList != null) filteredNewsSet.addAll(animalsCategoryList)
        if (eventsCategoryList != null) filteredNewsSet.addAll(eventsCategoryList)

        _newsItems.value = filteredNewsSet.toList()

        _isChildrenChecked.value = isChildrenChecked
        _isAdultsChecked.value = isAdultsChecked
        _isElderlyChecked.value = isElderlyChecked
        _isAnimalsChecked.value = isAnimalsChecked
        _isEventsChecked.value = isEventsChecked
    }

    companion object {
        private const val NEWS_ITEMS_JSON_FILE_NAME = "news_items.json"
        private const val THREAD_SLEEP_MILLIS : Long = 2000
    }

}
