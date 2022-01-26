package com.lealpy.simbirsoft_training.presentation.screens.news.news_description

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.domain.use_cases.news.GetFromDbNewsDescriptionItemByIdUseCase
import com.lealpy.simbirsoft_training.presentation.model.NewsDescriptionItemUi
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import com.lealpy.simbirsoft_training.utils.PresentationUtils.Companion.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsDescriptionViewModel @Inject constructor (
    private val getFromDbNewsDescriptionItemByIdUseCase: GetFromDbNewsDescriptionItemByIdUseCase,
    private val utils: PresentationUtils,
) : ViewModel() {

    private val _newsDescriptionItemUi = MutableLiveData <NewsDescriptionItemUi> ()
    val newsDescriptionItemUi : LiveData<NewsDescriptionItemUi> = _newsDescriptionItemUi

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _finishFragment = MutableLiveData <Boolean> ()
    val finishFragment : LiveData<Boolean> = _finishFragment

    fun onSiteClicked() { showToast() }

    fun onDonateThingsClicked() { showToast() }

    fun onBecomeVolunteerClicked() { showToast() }

    fun onProfHelpClicked() { showToast() }

    fun onDonateMoneyClicked() { showToast() }

    fun onShareClicked() { showToast() }

    fun onSpanSiteClicked() { showToast() }

    fun onSpanFeedbackClicked() { showToast() }

    fun getId(newsItemId: Long) {
        getNewsDescriptionItemFromDb(newsItemId)
    }

    private fun getNewsDescriptionItemFromDb(newsItemId: Long) {
        _progressBarVisibility.value = View.VISIBLE

        getFromDbNewsDescriptionItemByIdUseCase.execute(newsItemId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { newsDescriptionItem ->
                    val newsDescriptionItemUi = utils.newsDescriptionItemToNewsDescriptionItemUi(newsDescriptionItem)
                    _newsDescriptionItemUi.postValue(newsDescriptionItemUi)
                    _progressBarVisibility.postValue(View.GONE)
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    _progressBarVisibility.postValue(View.GONE)
                }
            )

    }

    private fun showToast() {
        utils.showToast(utils.getString(R.string.click_heard))
    }

}
