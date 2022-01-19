package com.lealpy.simbirsoft_training.ui.help

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.lealpy.simbirsoft_training.HelpApp
import com.lealpy.simbirsoft_training.database.help.HelpEntity
import com.lealpy.simbirsoft_training.database.help.HelpRepository
import com.lealpy.simbirsoft_training.utils.AppUtils
import com.lealpy.simbirsoft_training.utils.AppUtils.LOG_TAG
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalStateException

class HelpViewModel(application: Application) : AndroidViewModel(application) {

    private val _helpItems = MutableLiveData<List<HelpItem>>()
    val helpItems: LiveData<List<HelpItem>> = _helpItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val requestManager = Glide.with(getApplication<Application>())

    private val compositeDisposable = CompositeDisposable()

    private val helpApi = (getApplication<Application>() as? HelpApp)?.helpApi

    private val helpDao = (application as? HelpApp)?.database?.helpDao()

    private val repository = HelpRepository(helpDao ?: throw IllegalStateException())

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

        helpApi?.let {
            compositeDisposable.add(
                helpApi.getHelpItemsJson()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .subscribe(
                        { helpItemsJsonFromServer ->
                            val helpEntities = AppUtils.helpItemsJsonToHelpEntities(helpItemsJsonFromServer)
                            insertHelpEntitiesInDb(helpEntities)
                        },
                        { error ->
                            error.message?.let { err -> Log.e(LOG_TAG, err) }

                            val helpItemsJsonFromFile = AppUtils.getItemJsonFromFile<List<HelpItemJson>>(getApplication(), HELP_ITEMS_JSON_FILE_NAME)
                            val helpEntities = AppUtils.helpItemsJsonToHelpEntities(helpItemsJsonFromFile)
                            insertHelpEntitiesInDb(helpEntities)
                        }
                    )
            )
        }
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
                        val helpItems = AppUtils.helpEntitiesToHelpItems(helpEntities, requestManager)
                        _helpItems.postValue(helpItems)
                        _progressBarVisibility.postValue(View.GONE)
                    },
                    { error ->
                        error.message?.let { err -> Log.e(LOG_TAG, err) }
                    }
                )
        )
    }

    companion object {
        private const val HELP_ITEMS_JSON_FILE_NAME = "help_items.json"
    }

}
