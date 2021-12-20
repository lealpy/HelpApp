package com.lealpy.simbirsoft_training.ui.news

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val jsonFileString = getJsonDataFromAsset(application, NEWS_ITEMS_JSON_FILE_NAME)
    private val gson = Gson()
    private val itemTypes = object : TypeToken<List<NewsItem>>() {}.type
    private val newsItemsFromJson : List<NewsItem> = gson.fromJson(jsonFileString, itemTypes)

    private val _newsItems = MutableLiveData( newsItemsFromJson )
    val newsItems: LiveData<List<NewsItem>> = _newsItems

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

    private fun getJsonDataFromAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    fun applyFilters(
        isChildrenChecked: Boolean,
        isAdultsChecked: Boolean,
        isElderlyChecked: Boolean,
        isAnimalsChecked: Boolean,
        isEventsChecked: Boolean
    ) {
        val childrenCategoryList = if(isChildrenChecked) {
            newsItemsFromJson.filter { it.isChildrenCategory }
        } else emptyList()

        val adultCategoryList = if(isAdultsChecked) {
            newsItemsFromJson.filter { it.isAdultsCategory }
        } else emptyList()

        val elderlyCategoryList = if(isElderlyChecked) {
            newsItemsFromJson.filter { it.isElderlyCategory }
        } else emptyList()

        val animalsCategoryList = if(isAnimalsChecked) {
            newsItemsFromJson.filter { it.isAnimalsCategory }
        } else emptyList()

        val eventsCategoryList = if(isEventsChecked) {
            newsItemsFromJson.filter { it.isEventsCategory }
        } else emptyList()

        val filteredNewsSet = mutableSetOf<NewsItem>()

        filteredNewsSet.addAll(childrenCategoryList)
        filteredNewsSet.addAll(adultCategoryList)
        filteredNewsSet.addAll(elderlyCategoryList)
        filteredNewsSet.addAll(animalsCategoryList)
        filteredNewsSet.addAll(eventsCategoryList)

        _newsItems.value = filteredNewsSet.toList()

        _isChildrenChecked.value = isChildrenChecked
        _isAdultsChecked.value = isAdultsChecked
        _isElderlyChecked.value = isElderlyChecked
        _isAnimalsChecked.value = isAnimalsChecked
        _isEventsChecked.value = isEventsChecked
    }

    companion object {
        private const val NEWS_ITEMS_JSON_FILE_NAME = "news_items.json"
    }

}