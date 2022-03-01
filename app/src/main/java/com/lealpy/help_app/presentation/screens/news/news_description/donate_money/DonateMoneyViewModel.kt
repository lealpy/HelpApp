package com.lealpy.help_app.presentation.screens.news.news_description.donate_money

import android.app.Application
import android.text.Editable
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.use_cases.news.StartDonateNotificationWorkerUseCase
import com.lealpy.help_app.domain.use_cases.prefs.GetSettingGetPushUseCase
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_AMOUNT_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_OFFER_1_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_OFFER_2_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_OFFER_3_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_DONATION_OFFER_4_KEY
import com.lealpy.help_app.presentation.utils.Const.DONATE_MONEY_FEATURE_IS_FIRST_NOTIFICATION_KEY
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_ID_KEY
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_TITLE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DonateMoneyViewModel @Inject constructor(
    application: Application,
    private val savedStateHandle: SavedStateHandle,
    private val getSettingGetPushUseCase: GetSettingGetPushUseCase,
    private val startDonateNotificationWorkerUseCase: StartDonateNotificationWorkerUseCase,
) : AndroidViewModel(application) {

    private val _donationText = MutableLiveData<String>()
    val donationText: LiveData<String> = _donationText

    private val _selectedButton = MutableLiveData<String>()
    val selectedButton: LiveData<String> = _selectedButton

    private val _isPositiveButtonEnabled = MutableLiveData<Boolean>(false)
    val isPositiveButtonEnabled: LiveData<Boolean> = _isPositiveButtonEnabled

    private val donationAmountOffer1 = getApplication<Application>()
        .getString(R.string.dialog_fragment_donate_money_donation_offer_1)
        .filter { it.isDigit() }

    private val donationAmountOffer2 = getApplication<Application>()
        .getString(R.string.dialog_fragment_donate_money_donation_offer_2)
        .filter { it.isDigit() }

    private val donationAmountOffer3 = getApplication<Application>()
        .getString(R.string.dialog_fragment_donate_money_donation_offer_3)
        .filter { it.isDigit() }

    private val donationAmountOffer4 = getApplication<Application>()
        .getString(R.string.dialog_fragment_donate_money_donation_offer_4)
        .filter { it.isDigit() }

    private val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun getTextChangedDisposable(textChangedDisposable: Observable<String>) {
        disposable.add(
            textChangedDisposable
                .subscribeOn(Schedulers.computation())
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { donationAmount ->
                    onTextChanged(donationAmount)
                }
        )
    }

    fun onPositiveButtonClicked(donationText: Editable) {
        if (donationText.toString().isDigitsOnly() && getSettingGetPushUseCase()) {
            startWorkManager(donationText.toString().toInt())
        }
    }

    fun onDonationOfferChecked(checkedId: Int, isChecked: Boolean) {
        if (isChecked) {
            when (checkedId) {
                R.id.donationOffer1 -> _donationText.postValue(donationAmountOffer1)
                R.id.donationOffer2 -> _donationText.postValue(donationAmountOffer2)
                R.id.donationOffer3 -> _donationText.postValue(donationAmountOffer3)
                R.id.donationOffer4 -> _donationText.postValue(donationAmountOffer4)
            }
        }
    }

    private fun onTextChanged(donationAmount: String) {
        when (donationAmount) {
            donationAmountOffer1 -> _selectedButton.postValue(
                DONATE_MONEY_FEATURE_DONATION_OFFER_1_KEY)
            donationAmountOffer2 -> _selectedButton.postValue(
                DONATE_MONEY_FEATURE_DONATION_OFFER_2_KEY)
            donationAmountOffer3 -> _selectedButton.postValue(
                DONATE_MONEY_FEATURE_DONATION_OFFER_3_KEY)
            donationAmountOffer4 -> _selectedButton.postValue(
                DONATE_MONEY_FEATURE_DONATION_OFFER_4_KEY)
            else -> _selectedButton.postValue("")
        }

        _isPositiveButtonEnabled.value = donationAmount.isDigitsOnly()
                && donationAmount.length in DONATION_MIN_DIGITS..DONATION_MAX_DIGITS
                && donationAmount.toInt() != 0
    }

    fun onSaveInstanceState(donationText: String) {
        _donationText.value = donationText
    }

    private fun startWorkManager(donationAmount: Int) {
        startDonateNotificationWorkerUseCase(
            NEWS_FEATURE_NEWS_ID_KEY to savedStateHandle.get<Long>(NEWS_FEATURE_NEWS_ID_KEY),
            NEWS_FEATURE_NEWS_TITLE_KEY to savedStateHandle.get<String>(NEWS_FEATURE_NEWS_TITLE_KEY),
            DONATE_MONEY_FEATURE_DONATION_AMOUNT_KEY to donationAmount,
            DONATE_MONEY_FEATURE_IS_FIRST_NOTIFICATION_KEY to true
        )
    }

    companion object {
        private const val DONATION_MIN_DIGITS = 1
        private const val DONATION_MAX_DIGITS = 7
    }

}
