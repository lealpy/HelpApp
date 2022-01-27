package com.lealpy.simbirsoft_training.presentation.screens.search.search_by_events

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.domain.model.EventItem
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_events.GetFromDbEventItemsByTitleUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_events.InsertToDbEventItemsUseCase
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import com.lealpy.simbirsoft_training.utils.PresentationUtils.Companion.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchByEventsViewModel @Inject constructor(
    private val insertToDbEventItemsUseCase: InsertToDbEventItemsUseCase,
    private val getFromDbEventItemsByTitleUseCase: GetFromDbEventItemsByTitleUseCase,
    private val utils: PresentationUtils
) : ViewModel() {

    private val _eventItems = MutableLiveData<List<EventItem>> ()
    val eventItems : LiveData<List<EventItem>> = _eventItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _blankSearchViewsVisibility = MutableLiveData<Int>()
    val blankSearchImageVisibility: LiveData<Int> = _blankSearchViewsVisibility

    private val _nothingFoundViewsVisibility = MutableLiveData<Int>()
    val nothingFoundViewsVisibility: LiveData<Int> = _nothingFoundViewsVisibility

    private val _searchViewQuery = MutableLiveData<String>()
    val searchViewQuery: LiveData<String> = _searchViewQuery

    private var searchText = ""

    init {
        getEventItemsFromServerOrFile()
    }

    fun onSearchChanged(searchText: String) {
        this.searchText = searchText
        search(searchText)
    }

    fun onRefreshSwiped() {
        getEventItemsFromServerOrFile()
    }

    fun onEventTabSelected() {
        _searchViewQuery.value = searchText
    }

    fun onSearchExampleClicked(searchExample : String) {
        searchText = searchExample
        _searchViewQuery.value = searchExample
    }

    fun onItemClicked() {
        utils.showToast(utils.getString(R.string.click_heard))
    }

    private fun getEventItemsFromServerOrFile() {
        _blankSearchViewsVisibility.value = View.GONE
        _progressBarVisibility.value = View.VISIBLE

        insertToDbEventItemsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe {
                if(searchText.isBlank()) {
                    _blankSearchViewsVisibility.postValue(View.VISIBLE)
                    _progressBarVisibility.postValue(View.GONE)
                } else {
                    search(searchText)
                }
            }
    }

    private fun search(searchQuery : String) {
        if(searchQuery.isNotBlank()) {
            _blankSearchViewsVisibility.value = View.GONE
            _progressBarVisibility.value = View.VISIBLE

            getFromDbEventItemsByTitleUseCase.execute(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { eventItems ->
                        showSearchResults(eventItems)
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                    }
                )
        }
        else {
            _eventItems.value = emptyList()
            _blankSearchViewsVisibility.value = View.VISIBLE
            _nothingFoundViewsVisibility.value = View.GONE
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

}
