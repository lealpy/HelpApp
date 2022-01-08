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

    private val _badgeNumber = MutableLiveData<Int>()
    val badgeNumber: LiveData<Int> = _badgeNumber

    private var loadedNewsItems = listOf<NewsItem>()

    private val viewedNewsId = mutableSetOf<Long>()

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

            loadedNewsItems = newsItemsFromJson.map { newsItemFromJSON ->
                val image = Glide
                    .with(getApplication<Application>())
                    .asBitmap()
                    .load(newsItemFromJSON.imageURL)
                    .submit()
                    .get()

                NewsItem(
                    id = newsItemFromJSON.id,
                    image = image,
                    title = newsItemFromJSON.title,
                    abbreviatedText = newsItemFromJSON.abbreviatedText,
                    date = newsItemFromJSON.date,
                    fundName = null,
                    address = null,
                    phone = null,
                    image2 = null,
                    image3 = null,
                    fullText = null,
                    isChildrenCategory = newsItemFromJSON.isChildrenCategory,
                    isAdultsCategory = newsItemFromJSON.isAdultsCategory,
                    isElderlyCategory = newsItemFromJSON.isElderlyCategory,
                    isAnimalsCategory = newsItemFromJSON.isAnimalsCategory,
                    isEventsCategory = newsItemFromJSON.isEventsCategory
                )
            }

            _newsItems.postValue(loadedNewsItems)
            _progressBarVisibility.postValue(View.INVISIBLE)
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

        val childrenCategoryList = if(isChildrenChecked) {
            loadedNewsItems.filter { it.isChildrenCategory }
        } else emptyList()

        val adultCategoryList = if(isAdultsChecked) {
            loadedNewsItems.filter { it.isAdultsCategory }
        } else emptyList()

        val elderlyCategoryList = if(isElderlyChecked) {
            loadedNewsItems.filter { it.isElderlyCategory }
        } else emptyList()

        val animalsCategoryList = if(isAnimalsChecked) {
            loadedNewsItems.filter { it.isAnimalsCategory }
        } else emptyList()

        val eventsCategoryList = if(isEventsChecked) {
            loadedNewsItems.filter { it.isEventsCategory }
        } else emptyList()

        val filteredNewsSet = mutableSetOf<NewsItem>()

        filteredNewsSet.addAll(childrenCategoryList)
        filteredNewsSet.addAll(adultCategoryList)
        filteredNewsSet.addAll(elderlyCategoryList)
        filteredNewsSet.addAll(animalsCategoryList)
        filteredNewsSet.addAll(eventsCategoryList)

        _newsItems.value = filteredNewsSet.toList()

        updateNewsBadge()

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
