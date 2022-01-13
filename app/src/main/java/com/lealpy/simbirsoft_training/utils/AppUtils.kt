package com.lealpy.simbirsoft_training.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.RequestManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.ui.help.HelpItem
import com.lealpy.simbirsoft_training.ui.help.HelpItemJson
import com.lealpy.simbirsoft_training.ui.news.NewsItem
import com.lealpy.simbirsoft_training.ui.news.NewsItemJson

object AppUtils {

    const val LOG_TAG = "HelpAppLog"

    inline fun <reified T> getItemJsonFromFile(context: Context, fileName: String) : T {
        val jsonFileString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonFileString, object: TypeToken<T>() {}.type)
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

    private fun getBitmapFromUrl(requestManager : RequestManager, url : String) : Bitmap {
        return requestManager
            .asBitmap()
            .load(url)
            .submit()
            .get()
    }

    fun helpItemsJsonToHelpItems(helpItemsJson: List<HelpItemJson>, requestManager : RequestManager) : List<HelpItem> {
        return helpItemsJson.map { helpItemJson ->
            val image = getBitmapFromUrl(requestManager, helpItemJson.imageURL)

            HelpItem(
                id = helpItemJson.id,
                image = image,
                text = helpItemJson.text
            )
        }
    }

    fun newsItemsJsonToNewsItems(newsItemsJson: List<NewsItemJson>, requestManager : RequestManager) : List<NewsItem> {
        return newsItemsJson.map { newsItemJson ->
            val image = getBitmapFromUrl(requestManager, newsItemJson.imageURL)

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

    fun newsItemJsonToNewsItem(newsItemJson: NewsItemJson, requestManager : RequestManager) : NewsItem {
        val image = getBitmapFromUrl(requestManager, newsItemJson.imageURL)
        val image2 = getBitmapFromUrl(requestManager, newsItemJson.image2URL)
        val image3 = getBitmapFromUrl(requestManager, newsItemJson.image3URL)

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
