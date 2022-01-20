package com.lealpy.simbirsoft_training.ui.news

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.lealpy.simbirsoft_training.HelpApp
import com.lealpy.simbirsoft_training.utils.AppUtils
import io.reactivex.disposables.CompositeDisposable

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

    private val compositeDisposable = CompositeDisposable()

    private val newsApi = (getApplication() as? HelpApp)?.newsApi

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

        newsApi?.let {
            compositeDisposable.add(newsApi
                .getNewsItemsJson()
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.schedulers.Schedulers.computation())
                .subscribe(
                    { newsItemsJsonFromServer ->
                        val newsItemsResult = AppUtils.newsItemsJsonToNewsItems(newsItemsJsonFromServer, requestManager)
                        loadedNewsItems = newsItemsResult
                        _newsItems.postValue(newsItemsResult)
                        _progressBarVisibility.postValue(View.GONE)
                    },
                    { error ->
                        error.message?.let { err -> Log.e(AppUtils.LOG_TAG, err) }

                        val newsItemsJsonFromFile = AppUtils.getItemJsonFromFile<List<NewsItemJson>>(getApplication(), NEWS_ITEMS_JSON_FILE_NAME)
                        val newsItemsResult = AppUtils.newsItemsJsonToNewsItems(newsItemsJsonFromFile, requestManager)
                        loadedNewsItems = newsItemsResult
                        _newsItems.postValue(newsItemsResult)
                        _progressBarVisibility.postValue(View.GONE)
                    }
                )
            )
        }
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
