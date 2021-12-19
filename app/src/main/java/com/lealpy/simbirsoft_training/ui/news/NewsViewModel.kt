package com.lealpy.simbirsoft_training.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.R

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val newsList = listOf(
        NewsItem(
            id = 0,
            image = R.drawable.news_item_1_image,
            title = application.getString(R.string.news_item_title_1),
            abbreviatedText = application.getString(R.string.news_item_1_abbreviated_text),
            date = application.getString(R.string.news_item_1_date),
            fundName = application.getString(R.string.news_item_1_fund_name),
            address = application.getString(R.string.news_item_1_address),
            phone = application.getString(R.string.news_item_1_phone),
            image2 = R.drawable.news_item_1_image_2,
            image3 = R.drawable.news_item_1_image_3,
            fullText = application.getString(R.string.news_item_1_full_text),
            isChildrenCategory = true,
            isAdultsCategory = false,
            isElderlyCategory = false,
            isAnimalsCategory = false,
            isEventsCategory = false
        ),
        NewsItem(
            id = 1,
            image = R.drawable.news_item_2_image,
            title = application.getString(R.string.news_item_2_title),
            abbreviatedText = application.getString(R.string.news_item_2_text),
            date = application.getString(R.string.news_item_2_date),
            fundName = application.getString(R.string.news_item_2_fund_name),
            address = application.getString(R.string.news_item_2_address),
            phone = application.getString(R.string.news_item_2_phone),
            image2 = R.drawable.news_item_1_image_2,
            image3 = R.drawable.news_item_1_image_3,
            fullText = application.getString(R.string.news_item_2_full_text),
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