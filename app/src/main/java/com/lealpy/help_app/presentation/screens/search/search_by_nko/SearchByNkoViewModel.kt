package com.lealpy.help_app.presentation.screens.search.search_by_nko

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.model.NkoItem
import com.lealpy.help_app.domain.use_cases.search_by_nko.GetFromDbNkoItemsByTitleUseCase
import com.lealpy.help_app.domain.use_cases.search_by_nko.UpdateNkoItemsUseCase
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import com.lealpy.help_app.presentation.utils.PresentationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchByNkoViewModel @Inject constructor(
    private val getFromDbNkoItemsByTitleUseCase: GetFromDbNkoItemsByTitleUseCase,
    private val updateNkoItemsUseCase: UpdateNkoItemsUseCase,
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

    private val disposable = CompositeDisposable()

    init {
        getNkoItems()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onNkoTabSelected() {
        _searchViewQuery.value = searchText

        if(_nkoItems.value == null) {
            getNkoItems()
        } else {
            val shuffledNkoItems = _nkoItems.value?.shuffled()
            _nkoItems.value = shuffledNkoItems
        }
    }

    fun onRefreshSwiped() {
        getNkoItems()
    }

    fun onSearchChanged(searchText: String) {
        this.searchText = searchText
        search(searchText)
    }

    fun onItemClicked() {
        utils.showToast(utils.getString(R.string.click_heard))
    }

    private fun getNkoItems() {
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            updateNkoItemsUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    search(searchText)
                }
        )
    }

    private fun search(searchQuery : String) {
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            getFromDbNkoItemsByTitleUseCase(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { nkoItems ->
                        setNumberOfFoundNkoItems(nkoItems.size)
                        _nkoItems.value = nkoItems
                        _progressBarVisibility.value = View.GONE
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

    private fun setNumberOfFoundNkoItems(numberOfFoundNkoItems : Int) {
        val searchResults = String.format(
            utils.getString(R.string.search_by_nko_search_results),
            numberOfFoundNkoItems
        )
        _searchResults.value = searchResults
    }

}
