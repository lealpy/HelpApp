package com.lealpy.help_app.presentation.screens.news.news_description

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.R
import com.lealpy.help_app.domain.use_cases.news.GetNewsDescriptionItemUseCase
import com.lealpy.help_app.presentation.model.NewsDescriptionItemUi
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_ID_KEY
import com.lealpy.help_app.presentation.utils.Const.NEWS_FEATURE_NEWS_TITLE_KEY
import com.lealpy.help_app.presentation.utils.PresentationMappers
import com.lealpy.help_app.presentation.utils.PresentationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsDescriptionViewModel @Inject constructor(
    private val getNewsDescriptionItemUseCase: GetNewsDescriptionItemUseCase,
    private val utils: PresentationUtils,
    private val savedStateHandle: SavedStateHandle,
    private val mappers: PresentationMappers,
) : ViewModel() {

    private val _newsDescriptionItemUi = MutableLiveData<NewsDescriptionItemUi>()
    val newsDescriptionItemUi: LiveData<NewsDescriptionItemUi> = _newsDescriptionItemUi

    private val _dialogArgs = MutableLiveData<Bundle>()
    val dialogArgs: LiveData<Bundle> = _dialogArgs

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val disposable = CompositeDisposable()

    init {
        getNewsDescriptionItem()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onSiteClicked() {
        showToast()
    }

    fun onDonateThingsClicked() {
        showToast()
    }

    fun onBecomeVolunteerClicked() {
        showToast()
    }

    fun onProfHelpClicked() {
        showToast()
    }

    fun onShareClicked() {
        showToast()
    }

    fun onSpanSiteClicked() {
        showToast()
    }

    fun onSpanFeedbackClicked() {
        showToast()
    }

    fun onDonateMoneyClicked() {
        val bundle = bundleOf(
            NEWS_FEATURE_NEWS_ID_KEY to _newsDescriptionItemUi.value?.id,
            NEWS_FEATURE_NEWS_TITLE_KEY to _newsDescriptionItemUi.value?.title
        )
        _dialogArgs.value = bundle
    }

    private fun getNewsDescriptionItem() {
        val newsItemId = savedStateHandle.get<Long>(NEWS_FEATURE_NEWS_ID_KEY) ?: 0
        _progressBarVisibility.value = View.VISIBLE

        disposable.add(
            getNewsDescriptionItemUseCase(newsItemId)
                .map { newsDescriptionItem ->
                    mappers.newsDescriptionItemToNewsDescriptionItemUi(newsDescriptionItem)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { newsDescriptionItemUi ->
                        _newsDescriptionItemUi.value = newsDescriptionItemUi
                        _progressBarVisibility.value = View.GONE
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

    private fun showToast() {
        utils.showToast(utils.getString(R.string.click_heard))
    }

}
