package com.lealpy.help_app.presentation.screens.news.news_preview

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.help_app.domain.use_cases.news.GetNewsPreviewItemsUseCase
import com.lealpy.help_app.domain.use_cases.news.InsertToDbWatchedNewsIdUseCase
import com.lealpy.help_app.domain.use_cases.news.GetUnwatchedNewsNumberUseCase
import com.lealpy.help_app.presentation.model.NewsPreviewItemUi
import com.lealpy.help_app.presentation.utils.Const.LOG_TAG
import com.lealpy.help_app.presentation.utils.PresentationMappers
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsPreviewViewModel @Inject constructor(
    private val getNewsPreviewItemsUseCase: GetNewsPreviewItemsUseCase,
    private val getUnwatchedNewsNumberUseCase: GetUnwatchedNewsNumberUseCase,
    private val insertToDbWatchedNewsIdUseCase : InsertToDbWatchedNewsIdUseCase,
    private val mappers: PresentationMappers,
) : ViewModel() {

    private val _newsPreviewItemsUi = MutableLiveData<List<NewsPreviewItemUi>>()
    val newsPreviewItemsUi: LiveData<List<NewsPreviewItemUi>> = _newsPreviewItemsUi

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _isChildrenChecked = MutableLiveData(true)
    val isChildrenChecked: LiveData<Boolean> = _isChildrenChecked

    private val _isAdultChecked = MutableLiveData(true)
    val isAdultChecked: LiveData<Boolean> = _isAdultChecked

    private val _isElderlyChecked = MutableLiveData(true)
    val isElderlyChecked: LiveData<Boolean> = _isElderlyChecked

    private val _isAnimalChecked = MutableLiveData(true)
    val isAnimalChecked: LiveData<Boolean> = _isAnimalChecked

    private val _isEventChecked = MutableLiveData(true)
    val isEventChecked: LiveData<Boolean> = _isEventChecked

    private val _badgeNumber = MutableLiveData<Int>()
    val badgeNumber: LiveData<Int> = _badgeNumber

    private var loadedNewsPreviewItemsUi = listOf<NewsPreviewItemUi>()

    private val disposable = CompositeDisposable()

    init {
        getNewsItems()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun applyFilters(
        isChildrenChecked: Boolean,
        isAdultChecked: Boolean,
        isElderlyChecked: Boolean,
        isAnimalChecked: Boolean,
        isEventChecked: Boolean,
    ) {
        val filteredNewsSet = mutableSetOf<NewsPreviewItemUi>()

        loadedNewsPreviewItemsUi.forEach { newsPreviewItemUi ->
            if(
                isChildrenChecked && newsPreviewItemUi.isChildrenCategory ||
                isAdultChecked && newsPreviewItemUi.isAdultCategory ||
                isElderlyChecked && newsPreviewItemUi.isElderlyCategory ||
                isAnimalChecked && newsPreviewItemUi.isAnimalCategory ||
                isEventChecked && newsPreviewItemUi.isEventCategory
            ) {
                filteredNewsSet.add(newsPreviewItemUi)
            }
        }

        _newsPreviewItemsUi.value = filteredNewsSet.toList()
        _isChildrenChecked.value = isChildrenChecked
        _isAdultChecked.value = isAdultChecked
        _isElderlyChecked.value = isElderlyChecked
        _isAnimalChecked.value = isAnimalChecked
        _isEventChecked.value = isEventChecked
    }

    fun onNewsWatched(id: Long) {
        insertToDbWatchedNewsId(id)
    }

    fun onSwipedRefresh() {
        getNewsItems()
    }

    private fun getNewsItems() {
        _progressBarVisibility.value = View.VISIBLE
        _isChildrenChecked.value = true
        _isAdultChecked.value = true
        _isElderlyChecked.value = true
        _isAnimalChecked.value = true
        _isEventChecked.value = true

        disposable.add(
            getNewsPreviewItemsUseCase()
                .map { newsPreviewItems ->
                    mappers.newsPreviewItemsToNewsPreviewItemsUi(newsPreviewItems)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { newsPreviewItemsUi ->
                        getFromDbUnwatchedNewsNumber()
                        loadedNewsPreviewItemsUi = newsPreviewItemsUi
                        _newsPreviewItemsUi.value = newsPreviewItemsUi
                        _progressBarVisibility.value = View.GONE
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                        _progressBarVisibility.value = View.GONE
                    }
                )
        )
    }

    private fun getFromDbUnwatchedNewsNumber() {
        disposable.add(
            getUnwatchedNewsNumberUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { unwatchedNewsNumber ->
                        _badgeNumber.value = unwatchedNewsNumber
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                    }
                )
        )
    }

    private fun insertToDbWatchedNewsId (newsId : Long) {
        disposable.add(
            insertToDbWatchedNewsIdUseCase(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        getFromDbUnwatchedNewsNumber()
                    },
                    { error ->
                        Log.e(LOG_TAG, error.message.toString())
                    }
                )
        )
    }

}
