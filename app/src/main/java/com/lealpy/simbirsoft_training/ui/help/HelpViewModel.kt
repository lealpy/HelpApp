package com.lealpy.simbirsoft_training.ui.help

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.HelpApp
import com.lealpy.simbirsoft_training.utils.AppUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HelpViewModel(application: Application) : AndroidViewModel(application) {

    private val _helpItems = MutableLiveData<List<HelpItem>>()
    val helpItems: LiveData<List<HelpItem>> = _helpItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val compositeDisposable = CompositeDisposable()

    private val helpApi = (getApplication<Application>() as? HelpApp)?.helpApi

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    private fun fetchHelpItems() {
        _progressBarVisibility.value = View.VISIBLE

        helpApi?.let {
            compositeDisposable.add(helpApi
                .getHelpItemsJson()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { helpItemsJsonFromServer ->
                        val helpItemsResult = AppUtils.helpItemsJsonToHelpItems(getApplication(), helpItemsJsonFromServer)
                        _helpItems.postValue(helpItemsResult)
                        _progressBarVisibility.postValue(View.INVISIBLE)
                    },
                    { error ->
                        error.message?.let { err -> Log.e(AppUtils.LOG_TAG, err) }

                        val helpItemsJsonFromFile = AppUtils.getItemJsonFromFile<List<HelpItemJson>>(getApplication(), HELP_ITEMS_JSON_FILE_NAME)
                        val helpItemsResult = AppUtils.helpItemsJsonToHelpItems(getApplication(), helpItemsJsonFromFile)
                        _helpItems.postValue(helpItemsResult)
                        _progressBarVisibility.postValue(View.INVISIBLE)
                    }
                )
            )
        }
    }

    fun onViewCreated() {
        if(_helpItems.value == null) fetchHelpItems()
    }

    fun onSwipedRefresh() {
        fetchHelpItems()
    }

    companion object {
        private const val HELP_ITEMS_JSON_FILE_NAME = "help_items.json"
    }

}