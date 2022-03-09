package com.lealpy.help_app.presentation.screens.history

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.domain.model.DonationHistoryItem
import com.lealpy.help_app.domain.use_cases.news.GetDonationHistoryItemsUseCase
import com.lealpy.help_app.presentation.model.DonationHistoryItemUi
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import com.lealpy.help_app.presentation.utils.PresentationMappers
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getDonationHistoryItemsUseCase: GetDonationHistoryItemsUseCase,
    private val mappers: PresentationMappers,
) : ViewModel() {

    private val _donationHistoryItemsUi = MutableLiveData<List<DonationHistoryItemUi>>()
    val donationHistoryItems: LiveData<List<DonationHistoryItemUi>> = _donationHistoryItemsUi

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val disposable = CompositeDisposable()

    init {
        getDonationHistoryItems()
    }

    fun onSwipedRefresh() {
        getDonationHistoryItems()
    }

    private fun getDonationHistoryItems() {
        disposable.add(
            getDonationHistoryItemsUseCase()
                .map { donationHistoryItems ->
                    mappers.donationHistoryItemsToDonationHistoryItemsUi(donationHistoryItems)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { donationHistoryItemsUi ->
                        _donationHistoryItemsUi.value = donationHistoryItemsUi
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
