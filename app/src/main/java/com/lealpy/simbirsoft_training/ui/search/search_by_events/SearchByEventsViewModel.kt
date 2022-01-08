package com.lealpy.simbirsoft_training.ui.search.search_by_events

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.utils.AppUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

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

    private var searchText = ""

    fun onSearchChanged(searchText: String) {
        this.searchText = searchText
        search(searchText)
    }

    fun onRefreshSwiped() {
        search(searchText)
    }

    private fun getEventItemsFromJSON() : List<EventItem> {
        val jsonFileString = AppUtils.getJsonDataFromAsset(getApplication(),
            EVENT_ITEMS_JSON_FILE_NAME)
        val gson = Gson()
        val itemTypes = object : TypeToken<List<EventItem>>() {}.type
        val eventItemsFromJson : List<EventItem> = gson.fromJson(jsonFileString, itemTypes)
        return eventItemsFromJson
    }

    private fun search(searchText: String) {

        if(searchText.isNotBlank()) {

            _blankSearchViewsVisibility.value = View.INVISIBLE
            _progressBarVisibility.value = View.VISIBLE
            _recyclerViewVisibility.value = View.VISIBLE

            Observable.create<List<EventItem>> { emitter ->

                val filteredEventItemsFromJSON = getEventItemsFromJSON().filter { eventItemJSON ->
                    eventItemJSON.title.contains(searchText)
                }

                if (!emitter.isDisposed) {
                    emitter.onNext(filteredEventItemsFromJSON)
                }

            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { eventItems ->
                        _eventItems.postValue(eventItems)

                        _nothingFoundViewsVisibility.postValue(
                            if(eventItems.isEmpty()) View.VISIBLE
                            else View.INVISIBLE
                        )

                        _progressBarVisibility.postValue(View.INVISIBLE)

                    },
                    { error ->
                        throw Exception(error.message)
                    }
                )

        }

        else {
            _blankSearchViewsVisibility.value = View.VISIBLE
            _nothingFoundViewsVisibility.value = View.INVISIBLE
            _recyclerViewVisibility.value = View.INVISIBLE
        }

    }

    companion object {
        private const val EVENT_ITEMS_JSON_FILE_NAME = "event_items.json"
    }

}
