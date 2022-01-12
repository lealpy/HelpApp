package com.lealpy.simbirsoft_training.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.lealpy.simbirsoft_training.ui.help.HelpItem
import com.lealpy.simbirsoft_training.ui.help.HelpItemJson
import com.lealpy.simbirsoft_training.ui.news.NewsItem
import com.lealpy.simbirsoft_training.ui.news.NewsItemJson
import java.io.FileWriter

object AppUtils {

    const val LOG_TAG = "HelpAppLog"

    inline fun <reified T> getItemJsonFromFile(context: Context, fileName: String) : T {
        val jsonFileString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonFileString, object: TypeToken<T>() {}.type)
    }

    inline fun <reified T> saveItemJsonToFile(items: T, fileName: String)  {
        val writer = FileWriter(fileName)
        val gson = GsonBuilder().create()
        gson.toJson(items, writer)
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

    fun helpItemsJsonToHelpItems(context: Context, helpItemsJson: List<HelpItemJson>) : List<HelpItem> {
        return helpItemsJson.map { helpItemJson ->

            val image = Glide
                .with(context)
                .asBitmap()
                .load(helpItemJson.imageURL)
                .submit()
                .get()

            HelpItem(
                id = helpItemJson.id,
                image = image,
                text = helpItemJson.text
            )
        }
    }

    fun newsItemsJsonToNewsItems(context: Context, newsItemsJson: List<NewsItemJson>) : List<NewsItem> {
        return newsItemsJson.map { newsItemJson ->
            val image = Glide
                .with(context)
                .asBitmap()
                .load(newsItemJson.imageURL)
                .submit()
                .get()

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

    fun newsItemJsonToNewsItem(context: Context, newsItemJson: NewsItemJson) : NewsItem {
        val image = Glide
            .with(context)
            .asBitmap()
            .load(newsItemJson.imageURL)
            .submit()
            .get()

        val image2 = Glide
            .with(context)
            .asBitmap()
            .load(newsItemJson.image2URL)
            .submit()
            .get()

        val image3 = Glide
            .with(context)
            .asBitmap()
            .load(newsItemJson.image3URL)
            .submit()
            .get()

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
