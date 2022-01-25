package com.lealpy.simbirsoft_training.presentation.screens.help

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.presentation.model.HelpItemUi
import com.lealpy.simbirsoft_training.domain.use_cases.help.GetFromDbHelpItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.help.SaveToDbHelpItemsUseCase
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import com.lealpy.simbirsoft_training.utils.PresentationUtils.Companion.LOG_TAG
import com.lealpy.simbirsoft_training.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HelpViewModel @Inject constructor(
    private val getFromDbHelpItemsUseCase: GetFromDbHelpItemsUseCase,
    private val saveToDbHelpItemsUseCase : SaveToDbHelpItemsUseCase,
    private val resourceManager: ResourceManager,
    private val utils: PresentationUtils
) : ViewModel() {

    private val _helpItems = MutableLiveData<List<HelpItemUi>>()
    val helpItemsUi: LiveData<List<HelpItemUi>> = _helpItems

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    fun onViewCreated() {
        if(_helpItems.value == null) getHelpItemsFromServerOrFile()
    }

    fun onSwipedRefresh() {
        getHelpItemsFromServerOrFile()
    }

    private fun getHelpItemsFromServerOrFile() {
        _progressBarVisibility.value = View.VISIBLE

        saveToDbHelpItemsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe {
                getFromDbHelpItems()
            }
    }

    private fun getFromDbHelpItems() {
        getFromDbHelpItemsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { helpItems ->
                    val helpItemsUi = utils.helpItemsToHelpItemsUi(helpItems)
                    _helpItems.postValue(helpItemsUi)
                    _progressBarVisibility.postValue(View.GONE)
                },
                { error ->
                    error.message?.let { err -> Log.e(LOG_TAG, err) }
                    _progressBarVisibility.postValue(View.GONE)
                }
            )
    }

    fun onItemClicked() {
        utils.showToast(resourceManager.getString(R.string.click_heard))
    }

}