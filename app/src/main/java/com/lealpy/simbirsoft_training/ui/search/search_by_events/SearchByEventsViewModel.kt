package com.lealpy.simbirsoft_training.ui.search.search_by_events

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.HelpApp
import com.lealpy.simbirsoft_training.utils.AppUtils
import io.reactivex.disposables.CompositeDisposable

class SearchByEventsViewModel(application: Application) : AndroidViewModel(application) {

    private val _eventItems = MutableLiveData<List<EventItem>> ()
    val eventItems : LiveData<List<EventItem>> = _eventItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _blankSearchViewsVisibility = MutableLiveData<Int>()
    val blankSearchImageVisibility: LiveData<Int> = _blankSearchViewsVisibility

    private val _nothingFoundViewsVisibility = MutableLiveData<Int>()
    val nothingFoundViewsVisibility: LiveData<Int> = _nothingFoundViewsVisibility

    private val _recyclerViewVisibility = MutableLiveData<Int>()
    val recyclerViewVisibility: LiveData<Int> = _recyclerViewVisibility

    private val _searchViewQuery = MutableLiveData<String>()
    val searchViewQuery: LiveData<String> = _searchViewQuery

    private var searchText = ""

    private val compositeDisposable = CompositeDisposable()

    private val eventApi = (getApplication<Application>() as? HelpApp)?.eventApi

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun onSearchChanged(searchText: String) {
        this.searchText = searchText
        search(searchText)
    }

    fun onRefreshSwiped() {
        search(searchText)
    }

    fun onEventTabSelected() {
        _searchViewQuery.value = searchText
    }

    fun onSearchExampleClicked(searchExample : String) {
        searchText = searchExample
        _searchViewQuery.value = searchExample
    }

    private fun search(searchText: String) {

        if(searchText.isNotBlank()) {

            _blankSearchViewsVisibility.value = View.INVISIBLE
            _progressBarVisibility.value = View.VISIBLE
            _recyclerViewVisibility.value = View.VISIBLE

            eventApi?.let {
                compositeDisposable.add(eventApi
                    .getEventItemsJson()
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .observeOn(io.reactivex.schedulers.Schedulers.computation())
                    .subscribe(
                        { eventItemsFromServer ->
                            showSearchResults(eventItemsFromServer, searchText)
                        },
                        { error ->
                            error.message?.let { err -> Log.e(AppUtils.LOG_TAG, err) }
                            val eventItemsFromFile = AppUtils.getItemJsonFromFile<List<EventItem>>(getApplication(), EVENT_ITEMS_JSON_FILE_NAME)
                            showSearchResults(eventItemsFromFile, searchText)
                        }
                    )
                )
            }

        }

        else {
            _blankSearchViewsVisibility.value = View.VISIBLE
            _nothingFoundViewsVisibility.value = View.INVISIBLE
            _recyclerViewVisibility.value = View.INVISIBLE
        }

    }

    private fun showSearchResults(eventItems : List<EventItem>, searchText: String) {
        val filteredEventItems = eventItems.filter { eventItem ->
            eventItem.title.contains(searchText)
        }

        _eventItems.postValue(filteredEventItems)

        _nothingFoundViewsVisibility.postValue(
            if(filteredEventItems.isEmpty()) View.VISIBLE
            else View.INVISIBLE
        )

        _progressBarVisibility.postValue(View.INVISIBLE)
    }

    companion object {
        private const val EVENT_ITEMS_JSON_FILE_NAME = "event_items.json"
    }

}
