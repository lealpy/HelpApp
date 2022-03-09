package com.lealpy.help_app.presentation.screens.search.search_by_events

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.model.EventItem
import com.lealpy.help_app.domain.use_cases.search_by_events.GetFromDbEventItemsByTitleUseCase
import com.lealpy.help_app.domain.use_cases.search_by_events.UpdateEventItemsUseCase
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import com.lealpy.help_app.presentation.utils.PresentationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchByEventsViewModel @Inject constructor(
    private val updateEventItemsUseCase: UpdateEventItemsUseCase,
    private val getFromDbEventItemsByTitleUseCase: GetFromDbEventItemsByTitleUseCase,
    private val utils: PresentationUtils,
) : ViewModel() {

    private val _eventItems = MutableLiveData<List<EventItem>>()
    val eventItems: LiveData<List<EventItem>> = _eventItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _blankSearchViewsVisibility = MutableLiveData<Int>()
    val blankSearchImageVisibility: LiveData<Int> = _blankSearchViewsVisibility

    private val _nothingFoundViewsVisibility = MutableLiveData<Int>()
    val nothingFoundViewsVisibility: LiveData<Int> = _nothingFoundViewsVisibility

    private val _searchViewQuery = MutableLiveData<String>()
    val searchViewQuery: LiveData<String> = _searchViewQuery

    private var searchText = ""

    private val disposable = CompositeDisposable()

    init {
        updateEventItems()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onSearchChanged(searchText: String) {
        this.searchText = searchText
        search(searchText)
    }

    fun onRefreshSwiped() {
        updateEventItems()
    }

    fun onEventTabSelected() {
        _searchViewQuery.value = searchText
    }

    fun onSearchExampleClicked(searchExample: String) {
        searchText = searchExample
        _searchViewQuery.value = searchExample
    }

    fun onItemClicked() {
        utils.showToast(utils.getString(R.string.click_heard))
    }

    private fun updateEventItems() {
        _blankSearchViewsVisibility.value = View.GONE
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            updateEventItemsUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (searchText.isBlank()) {
                        _blankSearchViewsVisibility.value = View.VISIBLE
                        _progressBarVisibility.value = View.GONE
                    } else {
                        search(searchText)
                    }
                }
        )
    }

    private fun search(searchQuery: String) {
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            getFromDbEventItemsByTitleUseCase(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { eventItems ->
                        when {
                            searchQuery.isBlank() -> {
                                _blankSearchViewsVisibility.value = View.VISIBLE
                                _nothingFoundViewsVisibility.value = View.GONE
                            }
                            eventItems.isEmpty() -> {
                                _blankSearchViewsVisibility.value = View.GONE
                                _nothingFoundViewsVisibility.value = View.VISIBLE
                            }
                            else -> {
                                _blankSearchViewsVisibility.value = View.GONE
                                _nothingFoundViewsVisibility.value = View.GONE
                            }
                        }
                        _eventItems.value = eventItems
                        _progressBarVisibility.value = View.GONE
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

}
