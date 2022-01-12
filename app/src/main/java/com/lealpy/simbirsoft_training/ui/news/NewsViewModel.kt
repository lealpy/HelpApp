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

            val newsItemsResult = newsItemsFromJson.map { newsItemFromJSON ->

                val image = Glide
                    .with(getApplication<Application>())
                    .asBitmap()
                    .load(newsItemFromJSON.imageURL)
                    .submit()
                    .get()

                val image2 = Glide
                    .with(getApplication<Application>())
                    .asBitmap()
                    .load(newsItemFromJSON.image2URL)
                    .submit()
                    .get()

                val image3 = Glide
                    .with(getApplication<Application>())
                    .asBitmap()
                    .load(newsItemFromJSON.image3URL)
                    .submit()
                    .get()
                
                NewsItem(
                    id = newsItemFromJSON.id,
                    image = image,
                    title = newsItemFromJSON.title,
                    abbreviatedText = newsItemFromJSON.abbreviatedText,
                    date = newsItemFromJSON.date,
                    fundName = newsItemFromJSON.fundName,
                    address = newsItemFromJSON.address,
                    phone = newsItemFromJSON.phone,
                    image2 = image2,
                    image3 = image3,
                    fullText = newsItemFromJSON.fullText,
                    isChildrenCategory = newsItemFromJSON.isChildrenCategory,
                    isAdultsCategory = newsItemFromJSON.isAdultsCategory,
                    isElderlyCategory = newsItemFromJSON.isElderlyCategory,
                    isAnimalsCategory = newsItemFromJSON.isAnimalsCategory,
                    isEventsCategory = newsItemFromJSON.isEventsCategory
                )

            }

            _newsItems.postValue(newsItemsResult)
            _progressBarVisibility.postValue(View.INVISIBLE)
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
