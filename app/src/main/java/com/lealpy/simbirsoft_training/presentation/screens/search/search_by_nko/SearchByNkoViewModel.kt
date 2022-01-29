package com.lealpy.simbirsoft_training.presentation.screens.search.search_by_nko

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.domain.model.NkoItem
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_nko.GetFromDbAllNkoItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_nko.GetFromDbNkoItemsByTitleUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.search_by_nko.InsertToDbNkoItemsUseCase
import com.lealpy.simbirsoft_training.presentation.utils.PresentationUtils
import com.lealpy.simbirsoft_training.presentation.utils.PresentationUtils.Companion.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchByNkoViewModel @Inject constructor(
    private val getFromDbNkoItemsByTitleUseCase: GetFromDbNkoItemsByTitleUseCase,
    private val getFromDbAllNkoItemsUseCase: GetFromDbAllNkoItemsUseCase,
    private val insertToDbNkoItemsUseCase: InsertToDbNkoItemsUseCase,
    private val utils: PresentationUtils
) : ViewModel() {

    private val _nkoItems = MutableLiveData<List<NkoItem>> ()
    val nkoItems : LiveData<List<NkoItem>> = _nkoItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _searchViewQuery = MutableLiveData<String>()
    val searchViewQuery: LiveData<String> = _searchViewQuery

    private val _searchResults = MutableLiveData(
        String.format(utils.getString(R.string.search_by_nko_search_results), 0)
    )
    val searchResults: LiveData<String> = _searchResults

    private var searchText = ""

    init {
        getNkoItemsFromServerOrFile()
    }

    fun onNkoTabSelected() {
        _searchViewQuery.value = searchText

        if(_nkoItems.value == null) {
            getNkoItemsFromServerOrFile()
        }
        else {
            val shuffledNkoItems = _nkoItems.value?.shuffled()
            _nkoItems.value = shuffledNkoItems
        }
    }

    fun onRefreshSwiped() {
        getNkoItemsFromServerOrFile()
    }

    fun onSearchChanged(searchText: String) {
        this.searchText = searchText
        search(searchText)
    }

    fun onItemClicked() {
        utils.showToast(utils.getString(R.string.click_heard))
    }

    private fun getNkoItemsFromServerOrFile() {
        _progressBarVisibility.value = View.VISIBLE

        insertToDbNkoItemsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe {
                getFromDbAllNkoItems()
            }
    }

    private fun getFromDbAllNkoItems() {
        getFromDbAllNkoItemsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                { nkoItems ->
                    if(searchText.isBlank()) {
                        setNumberOfFoundNkoItems(nkoItems.size)
                        val shuffledNkoItems = nkoItems.shuffled()
                        _nkoItems.postValue(shuffledNkoItems)
                    } else {
                        search(searchText)
                    }
                    _progressBarVisibility.postValue(View.GONE)
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                }
            )
    }

    private fun search(searchQuery : String) {
        if(searchQuery.isNotBlank()) {
            _progressBarVisibility.value = View.VISIBLE

            getFromDbNkoItemsByTitleUseCase.execute(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { nkoItems ->
                        setNumberOfFoundNkoItems(nkoItems.size)
                        val shuffledNkoItems = nkoItems.shuffled()
                        _nkoItems.postValue(shuffledNkoItems)
                        _progressBarVisibility.postValue(View.GONE)
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                    }
                )
        }
        else {
            getFromDbAllNkoItems()
        }
    }

    private fun setNumberOfFoundNkoItems(numberOfFoundNkoItems : Int) {
        val searchResults = String.format(
            utils.getString(R.string.search_by_nko_search_results),
            numberOfFoundNkoItems
        )
        _searchResults.postValue(searchResults)
    }

}
