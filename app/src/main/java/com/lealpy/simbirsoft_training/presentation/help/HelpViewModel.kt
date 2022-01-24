package com.lealpy.simbirsoft_training.presentation.help

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.data.api.HelpApi
import com.lealpy.simbirsoft_training.data.database.help.HelpEntity
import com.lealpy.simbirsoft_training.data.database.help.HelpRepository
import com.lealpy.simbirsoft_training.data.model.HelpItemJson
import com.lealpy.simbirsoft_training.domain.model.HelpItem
import com.lealpy.simbirsoft_training.utils.AppUtils
import com.lealpy.simbirsoft_training.utils.AppUtils.Companion.LOG_TAG
import com.lealpy.simbirsoft_training.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HelpViewModel @Inject constructor(
    private val repository : HelpRepository,
    private val resourceManager: ResourceManager,
    private val helpApi: HelpApi,
    private val appUtils: AppUtils
) : ViewModel() {

    private val _helpItems = MutableLiveData<List<HelpItem>>()
    val helpItems: LiveData<List<HelpItem>> = _helpItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun onViewCreated() {
        if(_helpItems.value == null) getHelpItemsFromServerOrFile()
    }

    fun onSwipedRefresh() {
        getHelpItemsFromServerOrFile()
    }

    private fun getHelpItemsFromServerOrFile() {
        _progressBarVisibility.value = View.VISIBLE

        compositeDisposable.add(
            helpApi.getHelpItemsJson()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { helpItemsJsonFromServer ->
                        val helpEntities = appUtils.helpItemsJsonToHelpEntities(helpItemsJsonFromServer)
                        insertHelpEntitiesInDb(helpEntities)
                    },
                    { error ->
                        error.message?.let { err -> Log.e(LOG_TAG, err) }

                        val helpItemsJsonFromFile = appUtils.getItemJsonFromFile<List<HelpItemJson>>(HELP_ITEMS_JSON_FILE_NAME)
                        val helpEntities = appUtils.helpItemsJsonToHelpEntities(helpItemsJsonFromFile)
                        insertHelpEntitiesInDb(helpEntities)
                    }
                )
        )
    }

    private fun insertHelpEntitiesInDb(helpEntities: List<HelpEntity>) {
        _progressBarVisibility.postValue(View.VISIBLE)

        compositeDisposable.add(repository.insertHelpEntities(helpEntities)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    getHelpItemsFromDb()
                },
                { error ->
                    error.message?.let { err -> Log.e(LOG_TAG, err) }
                }
            )
        )
    }

    private fun getHelpItemsFromDb() {
        _progressBarVisibility.postValue(View.VISIBLE)

        compositeDisposable.add(
            repository.getAllHelpEntities()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                    { helpEntities ->
                        val helpItems = appUtils.helpEntitiesToHelpItems(helpEntities)
                        _helpItems.postValue(helpItems)
                        _progressBarVisibility.postValue(View.GONE)
                    },
                    { error ->
                        error.message?.let { err -> Log.e(LOG_TAG, err) }
                    }
                )
        )
    }

    fun onItemClicked() {
        appUtils.showToast(resourceManager.getString(R.string.click_heard))
    }

    companion object {
        private const val HELP_ITEMS_JSON_FILE_NAME = "help_items.json"
    }

}
