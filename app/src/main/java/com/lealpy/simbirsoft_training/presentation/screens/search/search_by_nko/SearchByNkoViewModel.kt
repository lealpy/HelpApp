package com.lealpy.simbirsoft_training.presentation.screens.search.search_by_nko

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.domain.model.NkoItem
import com.lealpy.simbirsoft_training.domain.use_cases.nko.GetFromDbAllNkoItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.nko.GetFromDbNkoItemsByTitleUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.nko.SaveToDbNkoItemsUseCase
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import com.lealpy.simbirsoft_training.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchByNkoViewModel @Inject constructor(
    private val getFromDbNkoItemsByTitleUseCase: GetFromDbNkoItemsByTitleUseCase,
    private val getFromDbAllNkoItemsUseCase: GetFromDbAllNkoItemsUseCase,
    private val saveToDbNkoItemsUseCase: SaveToDbNkoItemsUseCase,
    private val utils: PresentationUtils,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _nkoItems = MutableLiveData<List<NkoItem>> ()
    val nkoItems : LiveData<List<NkoItem>> = _nkoItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _searchViewQuery = MutableLiveData<String>()
    val searchViewQuery: LiveData<String> = _searchViewQuery

    private val _searchResults = MutableLiveData(
        String.format(resourceManager.getString(R.string.search_by_nko_search_results), 0)
    )
    val searchResults: LiveData<String> = _searchResults

    private var searchText = ""

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
        utils.showToast(resourceManager.getString(R.string.click_heard))
    }

    private fun getNkoItemsFromServerOrFile() {
        _progressBarVisibility.value = View.VISIBLE

        saveToDbNkoItemsUseCase.execute()
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
                    error.message?.let { err -> Log.e(PresentationUtils.LOG_TAG, err) }
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
                        error.message?.let { err -> Log.e(PresentationUtils.LOG_TAG, err) }
                    }
                )
        }
        else {
            getFromDbAllNkoItems()
        }
    }

    private fun setNumberOfFoundNkoItems(numberOfFoundNkoItems : Int) {
        val searchResults = String.format(
            resourceManager.getString(R.string.search_by_nko_search_results),
            numberOfFoundNkoItems
        )
        _searchResults.postValue(searchResults)
    }

}
