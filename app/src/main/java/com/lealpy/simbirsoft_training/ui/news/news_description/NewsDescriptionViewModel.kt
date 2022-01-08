package com.lealpy.simbirsoft_training.ui.news.news_description

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.ui.news.NewsItem
import com.lealpy.simbirsoft_training.ui.news.NewsItemJSON
import com.lealpy.simbirsoft_training.utils.AppUtils
import java.util.concurrent.Executors

class NewsDescriptionViewModel (application: Application) : AndroidViewModel(application) {

    private val _toastText = MutableLiveData <String> ()
    val toastText : LiveData<String> = _toastText

    private val _title = MutableLiveData <String> ()
    val title : LiveData<String> = _title

    private val _newsItem = MutableLiveData <NewsItem> ()
    val newsItem : LiveData<NewsItem> = _newsItem

    private val _progressBarVisibility = MutableLiveData<Int>()
    val progressBarVisibility: LiveData<Int> = _progressBarVisibility

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

    private fun getNewsItemFromJSON(newsItemId : Long) {

        val executorService = Executors.newSingleThreadExecutor()

        executorService.execute {
            _progressBarVisibility.postValue(View.VISIBLE)
            val jsonFileString = AppUtils.getJsonDataFromAsset(getApplication(),
                NEWS_ITEMS_JSON_FILE_NAME)
            val gson = Gson()
            val itemTypes = object : TypeToken<List<NewsItemJSON>>() {}.type
            val newsItemsFromJson : List<NewsItemJSON> = gson.fromJson(jsonFileString, itemTypes)

            val newsItemFromJSON = newsItemsFromJson.find { newsItem ->
                newsItem.id == newsItemId
            }

            val image = Glide
                .with(getApplication<Application>())
                .asBitmap()
                .load(newsItemFromJSON?.imageURL)
                .submit()
                .get()

            val image2 = Glide
                .with(getApplication<Application>())
                .asBitmap()
                .load(newsItemFromJSON?.image2URL)
                .submit()
                .get()

            val image3 = Glide
                .with(getApplication<Application>())
                .asBitmap()
                .load(newsItemFromJSON?.image3URL)
                .submit()
                .get()

            if(newsItemFromJSON != null) {
                val newsItemResult = NewsItem(
                    id = newsItemFromJSON.id,
                    image = image,
                    title = newsItemFromJSON.title,
                    abbreviatedText = newsItemFromJSON.abbreviatedText,
                    date = newsItemFromJSON.date,
                    fundName = newsItemFromJSON.fundName,
                    address = newsItemFromJSON.address,
                    phone = newsItemFromJSON.phone,
                    image2 = image2,
                    image3 = image3,
                    fullText = newsItemFromJSON.fullText,
                    isChildrenCategory = newsItemFromJSON.isChildrenCategory,
                    isAdultsCategory = newsItemFromJSON.isAdultsCategory,
                    isElderlyCategory = newsItemFromJSON.isElderlyCategory,
                    isAnimalsCategory = newsItemFromJSON.isAnimalsCategory,
                    isEventsCategory = newsItemFromJSON.isEventsCategory
                )
                _newsItem.postValue(newsItemResult)
            }

            _progressBarVisibility.postValue(View.INVISIBLE)
        }

        executorService.shutdown()

    }

    fun getId(newsItemId: Long) {
        getNewsItemFromJSON(newsItemId)
    }

    companion object {
        private const val NEWS_ITEMS_JSON_FILE_NAME = "news_items.json"
    }

}
