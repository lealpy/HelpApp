package com.lealpy.simbirsoft_training.presentation.search.search_by_events

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.data.api.EventApi
import com.lealpy.simbirsoft_training.data.database.events.EventEntity
import com.lealpy.simbirsoft_training.data.database.events.EventRepository
import com.lealpy.simbirsoft_training.domain.model.EventItem
import com.lealpy.simbirsoft_training.utils.AppUtils
import com.lealpy.simbirsoft_training.utils.AppUtils.Companion.LOG_TAG
import com.lealpy.simbirsoft_training.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchByEventsViewModel @Inject constructor(
    private val repository : EventRepository,
    private val resourceManager: ResourceManager,
    private val eventApi: EventApi,
    private val appUtils: AppUtils
) : ViewModel() {

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

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun onViewCreated() {
        if(_eventItems.value == null) getHelpItemsFromServerOrFile()
    }

    fun onSearchChanged(searchText: String) {
        this.searchText = searchText
        search(searchText)
    }

    fun onRefreshSwiped() {
        getHelpItemsFromServerOrFile()
    }

    fun onEventTabSelected() {
        _searchViewQuery.value = searchText
    }

    fun onSearchExampleClicked(searchExample : String) {
        searchText = searchExample
        _searchViewQuery.value = searchExample
    }

    private fun getHelpItemsFromServerOrFile() {
        _blankSearchViewsVisibility.value = View.GONE
        _progressBarVisibility.value = View.VISIBLE
        _recyclerViewVisibility.value = View.VISIBLE

        compositeDisposable.add(
            eventApi.getEventItemsJson()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { eventItemsFromServer ->
                        val eventEntities = appUtils.eventItemsToEventEntities(eventItemsFromServer)
                        insertEventsEntitiesInDb(eventEntities)
                    },
                    { error ->
                        error.message?.let { err -> Log.e(LOG_TAG, err) }
                        val eventItemsFromFile = appUtils.getItemJsonFromFile<List<EventItem>>(EVENT_ITEMS_JSON_FILE_NAME)
                        val eventEntities = appUtils.eventItemsToEventEntities(eventItemsFromFile)
                        insertEventsEntitiesInDb(eventEntities)
                    }
                )
        )
    }

    private fun insertEventsEntitiesInDb(eventsEntities: List<EventEntity>) {
        _progressBarVisibility.postValue(View.VISIBLE)

        compositeDisposable.add(repository.insertEventEntities(eventsEntities)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    _blankSearchViewsVisibility.postValue(View.VISIBLE)
                    _progressBarVisibility.postValue(View.GONE)
                },
                { error ->
                    error.message?.let { err -> Log.e(LOG_TAG, err) }
                }
            )
        )
    }

    private fun search(searchQuery : String) {
        if(searchQuery.isNotBlank()) {
            _blankSearchViewsVisibility.value = View.GONE
            _progressBarVisibility.value = View.VISIBLE
            _recyclerViewVisibility.value = View.VISIBLE

            compositeDisposable.add(
                repository.getEventEntitiesByTitle(searchQuery)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .subscribe(
                        { eventEntities ->
                            val eventItems = appUtils.eventEntitiesToEventItems(eventEntities)
                            showSearchResults(eventItems)
                        },
                        { error ->
                            error.message?.let { err -> Log.e(LOG_TAG, err) }
                        }
                    )
            )
        }
        else {
            _blankSearchViewsVisibility.value = View.VISIBLE
            _nothingFoundViewsVisibility.value = View.GONE
            _recyclerViewVisibility.value = View.GONE
        }
    }

    private fun showSearchResults(eventItems : List<EventItem>) {
        _eventItems.postValue(eventItems)
        _nothingFoundViewsVisibility.postValue(
            if(eventItems.isEmpty()) View.VISIBLE
            else View.GONE
        )
        _progressBarVisibility.postValue(View.GONE)
    }

    fun onItemClicked() {
        appUtils.showToast(resourceManager.getString(R.string.click_heard))
    }

    companion object {
        private const val EVENT_ITEMS_JSON_FILE_NAME = "event_items.json"
    }

}
