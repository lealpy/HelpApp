package com.lealpy.simbirsoft_training.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.R

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    val newsList = listOf(
        NewsItem(
            0,
            R.drawable.news_item_1_image,
            application.getString(R.string.news_item_title_1),
            application.getString(R.string.news_item_text_1),
            application.getString(R.string.news_item_date_1),
            isChildrenCategory = true,
            isAdultsCategory = false,
            isElderlyCategory = false,
            isAnimalsCategory = false,
            isEventsCategory = false
        ),
        NewsItem(
            1,
            R.drawable.news_item_2_image,
            application.getString(R.string.news_item_title_2),
            application.getString(R.string.news_item_text_2),
            application.getString(R.string.news_item_date_2),
            isChildrenCategory = true,
            isAdultsCategory = false,
            isElderlyCategory = false,
            isAnimalsCategory = false,
            isEventsCategory = true
        )
    )

    private val _newsItems = MutableLiveData(newsList)
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

    fun applyFilters(
        isChildrenChecked: Boolean,
        isAdultsChecked: Boolean,
        isElderlyChecked: Boolean,
        isAnimalsChecked: Boolean,
        isEventsChecked: Boolean
    ) {
        val childrenCategoryList = if(isChildrenChecked) {
            newsList.filter { it.isChildrenCategory }
        } else emptyList()

        val adultCategoryList = if(isAdultsChecked) {
            newsList.filter { it.isAdultsCategory }
        } else emptyList()

        val elderlyCategoryList = if(isElderlyChecked) {
            newsList.filter { it.isElderlyCategory }
        } else emptyList()

        val animalsCategoryList = if(isAnimalsChecked) {
            newsList.filter { it.isAnimalsCategory }
        } else emptyList()

        val eventsCategoryList = if(isEventsChecked) {
            newsList.filter { it.isEventsCategory }
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

}