package com.lealpy.simbirsoft_training.ui.search.search_by_events

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.HelpApp
import com.lealpy.simbirsoft_training.database.events.EventEntity
import com.lealpy.simbirsoft_training.database.events.EventRepository
import com.lealpy.simbirsoft_training.utils.AppUtils
import com.lealpy.simbirsoft_training.utils.AppUtils.LOG_TAG
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalStateException

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

    private val eventDao = (application as? HelpApp)?.database?.eventDao()

    private val repository = EventRepository(eventDao ?: throw IllegalStateException())

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

        eventApi?.let {
            compositeDisposable.add(
                eventApi.getEventItemsJson()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .subscribe(
                        { eventItemsFromServer ->
                            val eventEntities = AppUtils.eventItemsToEventEntities(eventItemsFromServer)
                            insertEventsEntitiesInDb(eventEntities)
                        },
                        { error ->
                            error.message?.let { err -> Log.e(LOG_TAG, err) }
                            val eventItemsFromFile = AppUtils.getItemJsonFromFile<List<EventItem>>(getApplication(), EVENT_ITEMS_JSON_FILE_NAME)
                            val eventEntities = AppUtils.eventItemsToEventEntities(eventItemsFromFile)
                            insertEventsEntitiesInDb(eventEntities)
                        }
                    )
            )
        }
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
                            val eventItems = AppUtils.eventEntitiesToEventItems(eventEntities)
                            showSearchResults(eventItems)
                        },
                        { error ->
                            error.message?.let { err -> Log.e(LOG_TAG, err) }
                        }
                    )
            )
        } else {
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

    companion object {
        private const val EVENT_ITEMS_JSON_FILE_NAME = "event_items.json"
    }

}
