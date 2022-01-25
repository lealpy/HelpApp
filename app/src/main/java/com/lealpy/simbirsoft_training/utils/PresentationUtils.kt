package com.lealpy.simbirsoft_training.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.data.database.events.EventEntity
import com.lealpy.simbirsoft_training.data.database.help.HelpEntity
import com.lealpy.simbirsoft_training.presentation.model.HelpItemUi
import com.lealpy.simbirsoft_training.domain.model.HelpItem
import com.lealpy.simbirsoft_training.presentation.model.NewsItemUi
import com.lealpy.simbirsoft_training.domain.model.NewsItem
import com.lealpy.simbirsoft_training.domain.model.EventItem
import javax.inject.Inject

class PresentationUtils @Inject constructor(
    val appContext: Context,
    private val requestManager: RequestManager
    ) {

    companion object {
        const val LOG_TAG = "HelpAppLog"
    }

    inline fun <reified T> getItemsFromFile(fileName: String) : T {
        val jsonFileString = appContext.assets.open(fileName).bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonFileString, object: TypeToken<T>() {}.type)
    }

    fun getBitmapFromUrl(url : String) : Bitmap {
        return requestManager
            .asBitmap()
            .load(url)
            .placeholder(R.drawable.no_photo)
            .error(R.drawable.no_photo)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(false)
            .submit()
            .get()
    }

    fun getBitmapFromUri(uri : Uri) : Bitmap {
        val inputStream = appContext.contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    fun cropBitmap(bitmap: Bitmap, ratio : Double) : Bitmap {
        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            (bitmap.width / ratio).toInt()
        )
    }

    fun showToast(text : String) {
        Toast.makeText(appContext, text, Toast.LENGTH_SHORT).show()
    }

    fun helpItemsToHelpItemsUi(helpItems : List<HelpItem>) : List<HelpItemUi> {
        return helpItems.map { helpItem ->
            val image = getBitmapFromUrl(helpItem.imageUrl)

            HelpItemUi(
                id = helpItem.id,
                image = image,
                text = helpItem.text
            )
        }
    }

    fun helpItemsJsonToHelpEntities(helpItems: List<HelpItem>) : List<HelpEntity> {
        return helpItems.map { helpItemJson ->
            HelpEntity(
                id = helpItemJson.id,
                imageUrl = helpItemJson.imageUrl,
                text = helpItemJson.text
            )
        }
    }

    fun helpEntitiesToHelpItems(helpEntities: List<HelpEntity>) : List<HelpItemUi> {
        return helpEntities.map { helpEntity ->
            val image = getBitmapFromUrl(helpEntity.imageUrl)

            HelpItemUi(
                id = helpEntity.id,
                image = image,
                text = helpEntity.text
            )
        }
    }

    fun eventItemsToEventEntities(eventItems: List<EventItem>) : List<EventEntity> {
        return eventItems.map { eventItem ->
            EventEntity(
                id = eventItem.id,
                title = eventItem.title,
                date = eventItem.date
            )
        }
    }

    fun eventEntitiesToEventItems(eventEntities: List<EventEntity>): List<EventItem> {
        return eventEntities.map { eventEntity ->
            EventItem(
                id = eventEntity.id,
                title = eventEntity.title,
                date = eventEntity.date
            )
        }
    }

    fun newsItemsJsonToNewsItems(newsItems: List<NewsItem>) : List<NewsItemUi> {
        return newsItems.map { newsItemJson ->
            val image = getBitmapFromUrl(newsItemJson.imageUrl)

            NewsItemUi(
                id = newsItemJson.id,
                image = image,
                title = newsItemJson.title,
                abbreviatedText = newsItemJson.abbreviatedText,
                date = newsItemJson.date,
                fundName = null,
                address = null,
                phone = null,
                image2 = null,
                image3 = null,
                fullText = null,
                isChildrenCategory = newsItemJson.isChildrenCategory,
                isAdultsCategory = newsItemJson.isAdultsCategory,
                isElderlyCategory = newsItemJson.isElderlyCategory,
                isAnimalsCategory = newsItemJson.isAnimalsCategory,
                isEventsCategory = newsItemJson.isEventsCategory
            )
        }
    }

    fun newsItemJsonToNewsItem(newsItem: NewsItem) : NewsItemUi {
        val image = getBitmapFromUrl(newsItem.imageUrl)
        val image2 = getBitmapFromUrl(newsItem.image2Url)
        val image3 = getBitmapFromUrl(newsItem.image3Url)

        return NewsItemUi(
            id = newsItem.id,
            image = image,
            title = newsItem.title,
            abbreviatedText = newsItem.abbreviatedText,
            date = newsItem.date,
            fundName = newsItem.fundName,
            address = newsItem.address,
            phone = newsItem.phone,
            image2 = image2,
            image3 = image3,
            fullText = newsItem.fullText,
            isChildrenCategory = newsItem.isChildrenCategory,
            isAdultsCategory = newsItem.isAdultsCategory,
            isElderlyCategory = newsItem.isElderlyCategory,
            isAnimalsCategory = newsItem.isAnimalsCategory,
            isEventsCategory = newsItem.isEventsCategory
        )
    }

}
