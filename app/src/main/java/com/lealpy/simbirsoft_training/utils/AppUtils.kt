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
import com.lealpy.simbirsoft_training.domain.model.HelpItem
import com.lealpy.simbirsoft_training.data.model.HelpItemJson
import com.lealpy.simbirsoft_training.domain.model.NewsItem
import com.lealpy.simbirsoft_training.data.model.NewsItemJson
import com.lealpy.simbirsoft_training.domain.model.EventItem
import javax.inject.Inject

class AppUtils @Inject constructor(
    val appContext: Context,
    private val requestManager: RequestManager
    ) {

    companion object {
        const val LOG_TAG = "HelpAppLog"
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

    inline fun <reified T> getItemJsonFromFile(fileName: String) : T {
        val jsonFileString = appContext.assets.open(fileName).bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonFileString, object: TypeToken<T>() {}.type)
    }

    fun helpItemsJsonToHelpItems(helpItemsJson: List<HelpItemJson>) : List<HelpItem> {
        return helpItemsJson.map { helpItemJson ->
            val image = getBitmapFromUrl(helpItemJson.imageURL)

            HelpItem(
                id = helpItemJson.id,
                image = image,
                text = helpItemJson.text
            )
        }
    }

    fun helpItemsJsonToHelpEntities(helpItemsJson: List<HelpItemJson>) : List<HelpEntity> {
        return helpItemsJson.map { helpItemJson ->
            HelpEntity(
                id = helpItemJson.id,
                imageUrl = helpItemJson.imageURL,
                text = helpItemJson.text
            )
        }
    }

    fun helpEntitiesToHelpItems(helpEntities: List<HelpEntity>) : List<HelpItem> {
        return helpEntities.map { helpEntity ->
            val image = getBitmapFromUrl(helpEntity.imageUrl)

            HelpItem(
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

    fun newsItemsJsonToNewsItems(newsItemsJson: List<NewsItemJson>) : List<NewsItem> {
        return newsItemsJson.map { newsItemJson ->
            val image = getBitmapFromUrl(newsItemJson.imageURL)

            NewsItem(
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

    fun newsItemJsonToNewsItem(newsItemJson: NewsItemJson) : NewsItem {
        val image = getBitmapFromUrl(newsItemJson.imageURL)
        val image2 = getBitmapFromUrl(newsItemJson.image2URL)
        val image3 = getBitmapFromUrl(newsItemJson.image3URL)

        return NewsItem(
            id = newsItemJson.id,
            image = image,
            title = newsItemJson.title,
            abbreviatedText = newsItemJson.abbreviatedText,
            date = newsItemJson.date,
            fundName = newsItemJson.fundName,
            address = newsItemJson.address,
            phone = newsItemJson.phone,
            image2 = image2,
            image3 = image3,
            fullText = newsItemJson.fullText,
            isChildrenCategory = newsItemJson.isChildrenCategory,
            isAdultsCategory = newsItemJson.isAdultsCategory,
            isElderlyCategory = newsItemJson.isElderlyCategory,
            isAnimalsCategory = newsItemJson.isAnimalsCategory,
            isEventsCategory = newsItemJson.isEventsCategory
        )
    }

}
