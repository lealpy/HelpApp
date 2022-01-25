package com.lealpy.simbirsoft_training.presentation.screens.search.search_by_nko

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.data.api.NkoApi
import com.lealpy.simbirsoft_training.domain.model.NkoItem
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import com.lealpy.simbirsoft_training.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchByNkoViewModel @Inject constructor(
    private val nkoApi: NkoApi,
    private val utils: PresentationUtils,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _nkoItems = MutableLiveData<List<NkoItem>> ()
    val nkoItems : LiveData<List<NkoItem>> = _nkoItems

    private var nkoItemsFromJson : List<NkoItem>? = null

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _searchViewQuery = MutableLiveData<String>()
    val searchViewQuery: LiveData<String> = _searchViewQuery

    private val _searchResults = MutableLiveData(
        String.format(resourceManager.getString(R.string.search_by_nko_search_results), 0)
    )
    val searchResults: LiveData<String> = _searchResults

    private var searchText = ""

    private val compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun fetchNkoItemsJson() {
        _progressBarVisibility.value = View.VISIBLE

        compositeDisposable.add(nkoApi
            .getNkoItems()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                { nkoItemsFromServer ->
                    setRandomNkoItems(nkoItemsFromServer)
                    _progressBarVisibility.postValue(View.GONE)
                },
                { error ->
                    error.message?.let { err -> Log.e(PresentationUtils.LOG_TAG, err) }
                    val nkoItemsFromFile = utils.getItemsFromFile<List<NkoItem>>(NKO_ITEMS_JSON_FILE_NAME)
                    setRandomNkoItems(nkoItemsFromFile)
                    _progressBarVisibility.postValue(View.GONE)
                }
            )
        )
    }

    private fun setRandomNkoItems(nkoItems : List<NkoItem>) {
        nkoItemsFromJson = nkoItems
        val randomNkoItems = getRandomNkoItems(nkoItems)
        _nkoItems.postValue(randomNkoItems)
    }

    private fun getRandomNkoItems(nkoItems : List<NkoItem>?) : List<NkoItem> {
        return if(nkoItems != null) {
            var randomNkoItems = nkoItems.toMutableList()
            randomNkoItems.shuffle()
            val randomListSize = Random().nextInt(randomNkoItems.size)
            randomNkoItems = randomNkoItems.filterIndexed { index, _ ->
                index <= randomListSize
            }.toMutableList()

            setNumberOfFoundNkoItems(randomNkoItems.size)

            randomNkoItems
        }
        else {
            setNumberOfFoundNkoItems(0)
            emptyList()
        }
    }

    private fun setNumberOfFoundNkoItems(numberOfFoundNkoItems : Int) {
        val searchResults = String.format(resourceManager.getString(
            R.string.search_by_nko_search_results),
            numberOfFoundNkoItems
        )
        _searchResults.postValue(searchResults)
    }

    fun onNkoTabSelected() {
        _searchViewQuery.value = searchText

        if(nkoItemsFromJson == null) {
            fetchNkoItemsJson()
        }
        else {
            _nkoItems.value = getRandomNkoItems(nkoItemsFromJson)
        }
    }

    fun onRefreshSwiped() {
        fetchNkoItemsJson()
    }

    fun onSearchChanged(searchText: String) {
        this.searchText = searchText
    }

    fun onItemClicked() {
        utils.showToast(resourceManager.getString(R.string.click_heard))
    }

    companion object {
        private const val NKO_ITEMS_JSON_FILE_NAME = "nko_items.json"
    }

}
