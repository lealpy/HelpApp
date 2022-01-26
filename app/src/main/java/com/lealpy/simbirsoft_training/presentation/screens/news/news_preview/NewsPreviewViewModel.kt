package com.lealpy.simbirsoft_training.presentation.screens.news.news_preview

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.simbirsoft_training.domain.use_cases.news.GetFromDbAllNewsPreviewItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.news.InsertToDbWatchedNewsIdUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.news.SaveToDbNewsItemsUseCase
import com.lealpy.simbirsoft_training.domain.use_cases.news.GetUnwatchedNewsNumberUseCase
import com.lealpy.simbirsoft_training.presentation.model.NewsPreviewItemUi
import com.lealpy.simbirsoft_training.utils.PresentationUtils
import com.lealpy.simbirsoft_training.utils.PresentationUtils.Companion.LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsPreviewViewModel @Inject constructor(
    private val getFromDbAllNewsPreviewItemsUseCase: GetFromDbAllNewsPreviewItemsUseCase,
    private val saveToDbNewsItemsUseCase: SaveToDbNewsItemsUseCase,
    private val insertToDbWatchedNewsIdUseCase : InsertToDbWatchedNewsIdUseCase,
    private val getFromDbUnwatchedNewsNumberUseCase: GetUnwatchedNewsNumberUseCase,
    private val utils: PresentationUtils
) : ViewModel() {

    private val _newsPreviewItemsUi = MutableLiveData<List<NewsPreviewItemUi>>()
    val newsPreviewItemsUi: LiveData<List<NewsPreviewItemUi>> = _newsPreviewItemsUi

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

    private val _isChildrenChecked = MutableLiveData(true)
    val isChildrenChecked: LiveData<Boolean> = _isChildrenChecked

    private val _isAdultsChecked = MutableLiveData(true)
    val isAdultsChecked: LiveData<Boolean> = _isAdultsChecked

    private val _isElderlyChecked = MutableLiveData(true)
    val isElderlyChecked: LiveData<Boolean> = _isElderlyChecked

    private val _isAnimalsChecked = MutableLiveData(true)
    val isAnimalsChecked: LiveData<Boolean> = _isAnimalsChecked

    private val _isEventsChecked = MutableLiveData(true)
    val isEventsChecked: LiveData<Boolean> = _isEventsChecked

    private val _badgeNumber = MutableLiveData<Int>()
    val badgeNumber: LiveData<Int> = _badgeNumber

    private var loadedNewsPreviewItemsUi = listOf<NewsPreviewItemUi>()

    private val watchedNewsId = mutableSetOf<Long>()

    init {
        getNewsItemsFromServerOrFile()
    }

    fun applyFilters(
        isChildrenChecked: Boolean,
        isAdultsChecked: Boolean,
        isElderlyChecked: Boolean,
        isAnimalsChecked: Boolean,
        isEventsChecked: Boolean,
    ) {
        val filteredNewsSet = mutableSetOf<NewsPreviewItemUi>()

        loadedNewsPreviewItemsUi.forEach { newsPreviewItemUi ->
            if(
                isChildrenChecked && newsPreviewItemUi.isChildrenCategory ||
                isAdultsChecked && newsPreviewItemUi.isAdultCategory ||
                isElderlyChecked && newsPreviewItemUi.isElderlyCategory ||
                isAnimalsChecked && newsPreviewItemUi.isAnimalCategory ||
                isEventsChecked && newsPreviewItemUi.isEventCategory
            ) {
                filteredNewsSet.add(newsPreviewItemUi)
            }
        }

        _newsPreviewItemsUi.value = filteredNewsSet.toList()
        _isChildrenChecked.value = isChildrenChecked
        _isAdultsChecked.value = isAdultsChecked
        _isElderlyChecked.value = isElderlyChecked
        _isAnimalsChecked.value = isAnimalsChecked
        _isEventsChecked.value = isEventsChecked
    }

    fun onNewsWatched(id: Long) {
        watchedNewsId.add(id)
        insertToDbWatchedNewsId(id)
    }

    fun onSwipedRefresh() {
        getNewsItemsFromServerOrFile()
    }

    private fun getNewsItemsFromServerOrFile() {
        _progressBarVisibility.value = View.VISIBLE
        _isChildrenChecked.value = true
        _isAdultsChecked.value = true
        _isElderlyChecked.value = true
        _isAnimalsChecked.value = true
        _isEventsChecked.value = true

        saveToDbNewsItemsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe {
                getFromDbAllNewsPreviewItems()
                getFromDbUnwatchedNewsNumber()
            }
    }

    private fun getFromDbAllNewsPreviewItems() {
        getFromDbAllNewsPreviewItemsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { newsPreviewItems ->
                    val newsPreviewItemsUi = utils.newsPreviewItemsToNewsPreviewItemsUi(newsPreviewItems)
                    _newsPreviewItemsUi.postValue(newsPreviewItemsUi)
                    _progressBarVisibility.postValue(View.GONE)
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    _progressBarVisibility.postValue(View.GONE)
                }
            )
    }

    private fun insertToDbWatchedNewsId (newsId : Long) {
        insertToDbWatchedNewsIdUseCase.execute(newsId)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    getFromDbUnwatchedNewsNumber()
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                }
            )
    }

    private fun getFromDbUnwatchedNewsNumber() {
        getFromDbUnwatchedNewsNumberUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                { unwatchedNewsNumber ->
                    _badgeNumber.postValue(unwatchedNewsNumber)
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                }
            )
    }

}
