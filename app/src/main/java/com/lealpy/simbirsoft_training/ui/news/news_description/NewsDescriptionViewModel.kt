package com.lealpy.simbirsoft_training.ui.news.news_description

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.simbirsoft_training.R

class NewsDescriptionViewModel (application: Application) : AndroidViewModel(application) {

    private val _toastText = MutableLiveData <String> ()
    val toastText : LiveData<String> = _toastText

    private fun showToast() {
        _toastText.value = getApplication<Application>().getString(R.string.click_heard)
    }

    fun onSiteClicked() { showToast() }

    fun onDonateThingsClicked() { showToast() }

    fun onBecomeVolunteerClicked() { showToast() }

    fun onProfHelpClicked() { showToast() }

    fun onDonateMoneyClicked() { showToast() }

    fun onShareClicked() { showToast() }

    fun onSpanSiteClicked() { showToast() }

    fun onSpanFeedbackClicked() { showToast() }

}