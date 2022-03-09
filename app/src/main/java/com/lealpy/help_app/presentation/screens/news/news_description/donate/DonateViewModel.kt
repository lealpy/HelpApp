package com.lealpy.help_app.presentation.screens.news.news_description.donate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.R
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_FUND_NAME_KEY
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_PHONE_KEY
import com.lealpy.help_app.presentation.utils.PresentationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DonateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val utils: PresentationUtils,
) : ViewModel() {

    private val _dialogText = MutableLiveData<String>()
    val dialogText: LiveData<String> = _dialogText

    init {
        _dialogText.value = String.format(
            utils.getString(R.string.dialog_fragment_donate_dialog_text),
            savedStateHandle.get<String>(NEWS_FEATURE_NEWS_FUND_NAME_KEY) ?: "",
            savedStateHandle.get<String>(NEWS_FEATURE_NEWS_PHONE_KEY) ?: ""
        )
    }

}
