package com.lealpy.help_app.presentation.screens.help

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.use_cases.help.GetHelpItemsUseCase
import com.lealpy.help_app.presentation.model.HelpItemUi
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.lealpy.help_app.presentation.utils.PresentationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HelpViewModel @Inject constructor(
    private val getHelpItemsUseCase: GetHelpItemsUseCase,
    private val utils: PresentationUtils,
    private val mappers: PresentationMappers,
) : ViewModel() {

    private val _helpItemsUi = MutableLiveData<List<HelpItemUi>>()
    val helpItemsUi: LiveData<List<HelpItemUi>> = _helpItemsUi

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val disposable = CompositeDisposable()

    init {
        getHelpItems()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onSwipedRefresh() {
        getHelpItems()
    }

    fun onItemClicked() {
        utils.showToast(utils.getString(R.string.click_heard))
    }

    private fun getHelpItems() {
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            getHelpItemsUseCase()
                .map { helpItems ->
                    mappers.helpItemsToHelpItemsUi(helpItems)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { helpItemsUi ->
                        _helpItemsUi.value = helpItemsUi
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
